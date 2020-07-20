/**
 * 
 */
$(function() {
	// 获取productId
	var productId = getQueryString('productId');
	// 商品信息Url
	var infoUrl = '/eduo2o/shopadmin/getproductbyid?productId=' + productId;
	// 获取当前店铺设定的商店类别列表的URL
	var categoryUrl = '/eduo2o/shopadmin/getproductcategorylist';
	// 更新商品信息的url
	var productPostUrl = '/eduo2o/shopadmin/modifyproduct';
	//
	var isEdit = false;
	if (productId) {
		getInfo(productId);
		idEdit = true;
	} else {
		getCategory();
		productPostUrl = '/eduo2o/shopadmin/addproduct';
	}
	// 获取需要编辑的商品信息
	function getInfo(id) {
		$
				.getJSON(
						infoUrl,
						function(data) {
							if (data.success) {
								// 赋值表单
								var product = data.product;
								$('#product-name').val(product.productName);
								$('#product-desc').val(product.productDesc);
								$('#product-priority').val(product.priority);
								$('#normal-price').val(product.normalPrice);
								$('#promotion-price').val(
										product.promotionPrice);
								// 原本的商品类别，该店铺的所有商品类别列表
								var optionHtml = '';
								var optionArr = data.productCategoryList;
								var optionSelect = product.productCategory.productCategoryId;
								optionArr
										.map(function(item, index) {
											var isSelect = optionSelect === item.productCategoryId ? 'select'
													: '';
											optionHtml += '<option data-value="'
													+ item.productCategoryId
													+ '"'
													+ isSelect
													+ '>'
													+ item.productCategoryName
													+ '</option>'
										});
								$('#category').html(optionHtml);
							}
						})
	}
	// 添加商品时提供该店铺所有的商品类别
	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml)
			}
		});
	}
	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">')
		}
	})
	$('#submit').click(
			function() {
				var product = {};
				product.productName = $('#product-name').val();
				product.productDesc = $('#product-desc').val();
				product.priority = $('#product-priority').val();
				product.point = $('#point').val();
				product.normalPrice = $('#normal-price').val();
				product.promotionPrice = $('#promotion-price').val();
				product.productCategory = {
					productCategoryId : $('#category').find('option').not(
							function() {
								return !this.selected;
							}).data('value')
				};
				product.productId = productId;
				var thumbnail = $('#product-img')[0].files[0];
				// 表单
				var formData = new FormData();
				formData.append('thumbnail', thumbnail);
				$('.detail-img').map(
						function(index, item) {
							if ($('.detail-img')[index].files.length > 0) {
								formData.append('productImg' + index,
										$('.detail-img')[index].files[0]);
							}
						});
				formData.append('productStr', JSON.stringify(product));
				var verifyCodeActual = $('#j_captcha').val();
				if (!verifyCodeActual) {
					$.toast('请输入验证码！');
					return;
				}
				formData.append("verifyCodeActual", verifyCodeActual);
				$.ajax({
					url : productPostUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			})
})