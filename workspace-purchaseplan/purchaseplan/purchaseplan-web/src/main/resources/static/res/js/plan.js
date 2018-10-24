
 	/*点击加减号*/
 	function editNumber(){
 		$(".num-jia").click(function(){
 			
 			var inputNum = $(this).parents().siblings("li").find(".input-num").val();
 			var input = $(this).parents().siblings("li").find(".input-num");
 			var newNum = (parseInt(inputNum) + 1).toString();
 	        if (newNum > 0) {
 	        	input.val(newNum);
 	        } else {
 	        	input.val(0);
 	        }
 			
 		});
 		
 		
 		$(".num-jian").click(function(){
 			var inputNum = $(this).parents().siblings("li").find(".input-num").val();
 			var input = $(this).parents().siblings("li").find(".input-num");
 			var newNum = (parseInt(inputNum) - 1).toString();
 	        if (newNum > 0) {
 	        	input.val(newNum);
 	        } else {
 	            input.val(0);
 	        }
	
 		})
	 	

 		
 	}
 	editNumber();
 	
 	
 	
 	

