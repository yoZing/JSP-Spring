//팝업창들 뛰우기
//새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
							+"height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
	win.focus() ; 
}

// 팝업창 닫기
function CloseWindow() {
	window.opener.location.reload(true);
	window.close();
}
 
// 사용자 사진 넣기
function MemberPictureThumb(contextPath) { // (대상, 이미지 파일명)
	
	for(var target of document.querySelectorAll('.manPicture')) {
	
		var id = target.getAttribute('data-id');
	
		target.style.backgroundImage = "url('" + contextPath + "/member/getPicture.do?id=" + id + "')";
		target.style.backgroundPosition = "center";
		target.style.backgroundRepeat = "no-repeat";
		target.style.backgroundSize = "cover";
	}
}


// summmernote
var contextPath = "";

function summernote_go(target, context) {
	contextPath = context;
	
	target.summernote({
		placeholder:'여기에 내용을 적으세요.',
		lang:'ko-KR',
		height:250,
		disableResizeEditor:true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				for (var file of files) {
// 					alert(file.name);
					if (file.name.substring(file.name.lastIndexOf(".") + 1).toUpperCase() != "JPG") {
						alert("JPG 이미지형식만 가능합니다.")
						return;							
					}
					if (file.size > 1024 * 1024 * 5) {
						alert("이미지는 5MB 미만입니다.")
						return;
					}
				}
// 				alert("선택된 파일은 유효합니다.");
				for (var file of files) {
					sendFile(file, this);
				}
			}, /* onImageUpload */
			
			onMediaDelete : function(target) {
// 				alert("image delete");
// 				alert(target[0].src);
// 				alert(target.attr("src"));
				deleteFile(target[0].src);
			}/* onMediaDelete */
		}/* callback */
	});	
}

function sendFile(file, el) {
	var form_data = new FormData();
	form_data.append("file", file);
	$.ajax({
		url : contextPath + '/uploadImg.do',
		data : form_data,
		type : "POST",
		contentType : false,
		processData : false,
		success : function(img_url) {
			alert(img_url);
			$(el).summernote('editor.insertImage', img_url);
		},
		error : function() {
			alert(file.name + " 업로드에 실패했습니다.");
		}
	});
}

function deleteFile(src) {
	var splitSrc = src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
	
//	var fileData = {};
//	fileData.fileName = fileName;		
	var fileData = {fileName:fileName};  // 리터럴 객체 생성 방식.
	
	$.ajax({
		url : contextPath + '/deleteImg.do',
		data : JSON.stringify(fileData),
		type : "post",
		contentType : "application/json",
		success : function(res) {
			console.log(res);
		},
		error : function() {
			alert("이미지 삭제가 불가합니다.");
		}
	});
}































