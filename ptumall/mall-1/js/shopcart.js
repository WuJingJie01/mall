sessionStorage =  window.sessionStorage;
var cartArr = {} //key是cart的id,值是商品具体记录
let currentPageNum = 1
$(document).ready(function(){
	
	let is = isLogin()
	console.log(is)
	if(is){
		let id =  sessionStorage.getItem("userId")
		queryCart(1,10)
		bindPreNextPage()
	}
})




function isLogin(){
	let result = false
	$.ajax({
		type:"GET",
		url:"/user/isLogin",// /user/isLogin
		data:{},
		async:false,
		dataType:"json",
		success:function(res){
			if(res.code == 200){
				//已登陆
				$("#cartNoLogin").hide()
				$("#cartLogin").show()
				$("#nickName").html("你好" + res.data.nickName)
				sessionStorage.setItem("userId",res.data.id)
				result = true
			}else{
				//未登录
				$("#cartNoLogin").show()
				$("#cartLogin").hide()
				result = false
			}
		}
	})
	return result
}

function queryCart(pn ,pz){
	console.log("查询第" + pz + "页")
	let id = sessionStorage.getItem("userId")
	$.ajax({
		type:"GET",
		url:"/cart/list" ,// /cart/list
		data:{
			userId:id,
			pageNum:pn,
			pageSize:pz
		},
		dataType:"json",
		success:function(res){
			if(res.code == 200){
				console.log(res.data)
				let s = ""
				cartArr = {}
				for(let record of res.data){
					record.ischecked = false
					cartArr[record.id] = record
					s +=
					`<div class="cartd4" id="cartgood`+record.id+`" >
					<ul class="cartul2">
						<li class="cartli1">
							<div class="cartd3">
								<input type="checkbox" onclick="checkOneGood(`+record.id+`)" class="ipt">
							</div>
						</li>
						<li class="cartli2">
							<div class="fl">
								<img src="`+record.img+`">
							</div>
							<div class="f1 cartd5">`+record.prodName+`</div>
							<div class="cls"></div>
						</li>
						<li class="cartli3">￥`+record.price+`</li>
						<li class="cartli4">
							<button onclick="sub(`+record.id+`)">-</button>
							<input type="text" value="`+record.num+`" id="iid`+ record.id+`" />
							<button class="ml" onclick="add(`+ record.id+`)">+</button>
						</li>
						<li class="cartli5">￥<span id="gsum`+record.id+`">`+ (record.price * record.num) +`</span></li>
						<li class="cartli6">
							<a href="javascript:deleteCartGood(`+record.id+`)" style="text-decoration:none">删除</a>
						</li>
					</ul>
					</div>
					`	
				}
				$(".cartd4").remove()
				$("#cartgoodlist").append(s)
				currentPageNum = pn
				if(currentPageNum == 1){
					$("#prePage").prop("disabled",true)
				}else{
					$("#prePage").prop("disabled",false)
				}
				totalMoney()
				console.log(cartArr)
			}
		}
	})
}

function checkOneGood(id){
	cartArr[id].ischecked = !cartArr[id].ischecked
	totalMoney()
}

function totalMoney (){
	let total = 0
	let num = 0
	for(let key in cartArr){
		if(cartArr.hasOwnProperty(key)){
			let good = cartArr[key]
			if(good.ischecked){
				total += good.price * good.num
				num++
			}
		}
	}
	$("#totalNum").html(num)
	$("#totalPrice").html(total)
}

function checkAll(){
	let result = $("#sela").is(":checked")
	$(".ipt").prop("checked",result)
	for(let key in cartArr){
		if(cartArr.hasOwnProperty(key)){
			cartArr[key].ischecked = result
		}
	}
	totalMoney()
}

function sub(id){
	let snum =  $("#iid" + id).val()
	let num = parseInt(snum)
	if(num <= 1){
		// alert("不能更小了")
		showToast("不能更小了")

	}else{
		num = num - 1
		updateCart(id,num)
	}
}

function add(id){
	let snum =  $("#iid" + id).val()
	let num = parseInt(snum) + 1
	updateCart(id,num)
}

//更新数量
function updateCart(_id,_num){
	$.ajax({
		type:"GET" ,//POST
		url:"/cart/admin/update", // /cart/admin/update
		contentType:"application/json",
		data:JSON.stringify({
			id:_id,
			num:_num
		}),
		dataType:"json",
		success:function(res){
			if(res.code == 200){
				$("#iid" + _id).val(_num); //输入框更新
				cartArr[_id].num = _num // 更新全局变量carArr
				$("#gsum" + _id).html(_num * cartArr[_id].price) // 更新该行价格
				totalMoney() //更新总价
			}else{
				// alert("更新失败" + res.message)
				showToast("更新失败" + res.message)
			}
		}
	})
}

function deleteCartGood(id){
	let dataArr = [id]
	$.ajax({
		type:"GET" ,//POST
		url:"/cart/admin/delete", // /cart/admin/delete
		contentType:"application/json",
		data:JSON.stringify(dataArr),
		dataType:"json",
		success:function(res){
			if(res.code == 200){
				delete cartArr[id]// 删除全局变量carArr
				$("#cartgood" + id).remove()//删除元素
				totalMoney() //更新总价
			}else{
				// alert("删除失败" + res.message)
				showToast("删除失败" + res.message)
			}
		}
	})
}


function bindPreNextPage(){
	$("#prePage").on("click",function(){
		if(currentPageNum <= 1){
			// alert("已经是第一页")
			showToast("已经是第一页")
			return
		}
		let pageNum  = currentPageNum - 1
		queryCart(pageNum,10)
	})
	$("#nextPage").on("click",function(){
		let pageNum  = currentPageNum + 1
		queryCart(pageNum,10)
	})
}

function showToast(message){
	$("#messageToast").html(message)
	$("#liveToast").toast('show')
}