/**
 * 
 */
$(function() {

	var productId = getQueryString('productId');
	var productUrl = '/eduo2o/frontend/listproductdetailpageinfo?productId='
			+ productId;
	$.getJSON(productUrl, function(data) {
		if (data.success) {
			var product = data.product;
			$('#product-img').attr('src', product.imgAddr);
			$('#product-time').text(
					new Date(product.lastEditTime).Format("yyyy-MM-dd"));
			if (product.point != undefined) {
				$('#product-point').text('购买可得' + product.point + '积分');
			}
			$('#product-name').text(product.productName);
			$('#product-desc').text(product.productDesc);
			if (product.normalPrice != undefined
					&& product.promotionPrice != undefined) {
				$('#price').show();
				$('#normalPrice').html(
						'<del>' + '￥' + product.normalPrice + '</del>');
				$('#promotionPrice').text('￥' + product.promotionPrice);
			} else if (product.normalPrice != undefined
					&& product.promotionPrice == undefined) {
				$('#price').show();
				$('#promotionPrice').text('￥' + product.normalPrice);
			} else if (product.normalPrice == undefined
					&& product.promotionPrice != undefined) {
				$('#promotionPrice').text('￥' + product.promotionPrice);
			}
			var imgListHtml = '';
			product.productImgList.map(function(item, index) {
				imgListHtml += '<div> <img src="' + item.imgAddr
						+ '" width="100%" /></div>';
			})
			$('#imgList').html(imgListHtml);
		}
	})
	$('#me').click(function() {
		$.openPanel('#panel-js-demo');

	});
	$.init();
})