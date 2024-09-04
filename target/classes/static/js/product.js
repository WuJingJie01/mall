

$(document).ready(function(){
	
	function getQueryParam(name) {
	    let urlParams = new URLSearchParams(window.location.search);  
	    return urlParams.get(name);  
	}
	const productId = getQueryParam('id'); 
	console.log(`${productId}`);
	
	queryGoodsByCategoryId(productId)
})




function queryGoodsByCategoryId(productId){
	$.ajax({
		type:"GET",
		url:`/product/admin/${productId}`,
		data:"",
		dataType:"json",
		success:function(res){
			if(res.code == '200'){
				//图片
				let imgElement = document.querySelector('.product-image');
				imgElement.src = res.data?.img
				console.log(imgElement)
				//商品名称
				let proName = document.querySelector('.product-name');
				proName.innerText = res.data?.title
				//商品描述
				let proDescription = document.querySelector('.product-description')
				proDescription.innerText = res.data?.description
				//商品价格
				let proPrice = document.querySelector('.product-price')
				proPrice.innerText = "价格:" + res.data?.price
				//商品库存
				let proStocks = document.querySelector('.product-stocks')
				proStocks.innerText = "库存:" + res.data?.stocks
				//商品内容
				let proDetail = document.querySelector('.product-detail')
				proDetail.innerText = res.data?.detail
				//分类名
				let cateName = document.querySelector('.cateName');
				cateName.innerText = res.data?.categoryName + '-' + res.data?.title
			}
		}
	})
}