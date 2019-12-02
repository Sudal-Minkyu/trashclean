$(document).ready(function() {
	$('.nav-icon').on('click', function(e) {
		$('.wrap').toggleClass('nav--small');
		
		e.preventDefault();
	})
	
	
	// nav
	$('.nav-child').hide();
	$('.nav__link.toggle').on('click', function(e) {
		$(this).next().slideToggle(300);
		$('.nav__link.toggle').not(this).next().slideUp(300);
		
		return false
		/*var $this = $(this);
		
		if ($this.hasClass('active')) {
        	//$this.removeClass('active');
            $this.next().removeClass('active');
            $this.next().slideUp(350);
        } else {
        	//$this.parents('ul').find('li .nav__link.toggle').removeClass('active');
            $this.parents('ul').find('li .nav__child').removeClass('active');
            $this.parent().parent().find('li .nav__child').slideUp(350);
            //$this.toggleClass('active');
            $this.next().toggleClass('active');
            $this.next().slideToggle(350);
        }*/
		
		e.preventDefault();
	})
	
	// panel fold
	$('.panel__button.fold').on('click', function(e) {
		var foldArea = $(this).parents('.panel__header').next('.panel__body')
		
		if(foldArea.hasClass('fold')) {
			foldArea.removeClass('fold');
		} else {
			foldArea.addClass('fold');
		}
		

		e.preventDefault();
	})
	
	// btn-group
	$('.c-btn-group').on('click', '.c-btn-group__button', function(e) {
		var $this = $(this);
		var _index = $this.index();
		var $content = $('.c-btn-group__button').eq(_index);

		$this.add($content).addClass('active').siblings().removeClass('active');

		e.preventDefault();
	}).find('.c-btn-group__button').eq(0).trigger('click');
	
	// contents-profile
	$('.contents-profile__btn').on('click', function(e) {
		var context = $(this).next('.profile-context');
		
		if(context.hasClass('active')) {
			$(this).removeClass('active');
			context.removeClass('active');
		} else {
			$(this).addClass('active');
			context.addClass('active');
		}
		
		e.preventDefault();
		return false;
	})
	
	// 다른 영역 클릭시 팝업 닫기
	$('body').on('click', function() {
		$('.contents-profile__btn').removeClass('active');
		$('.profile-context').removeClass('active');
	})	
	$('.contents-profile').on('click', function(e) {
		e.stopPropagation();
	})

	//김민규추가 기존파일 : broadwave.ajax.js 추후정리(안쓰는함수는 주석처리)
	var globalAjaxLoginStatus = true;
	var Ajax = {
		requestForm : function(act_url, $form, callback, option) {
			var cfg = Ajax.getParam(act_url, null, callback, option);
			var ret = $form.ajaxSubmit(cfg);
		},
		request : function(act_url, act_data, callback, option) {

			var cfg = Ajax.getParam(act_url, act_data, callback, option);

			var is_loading = Ajax.is_loading(option);
			if (is_loading) Modal.showLoading();
			$.ajax(cfg);
		},
		is_loading : function(option) {
			if (typeof(option) == 'undefined') option = {};
			var is_loading = true;
			if (typeof(option['loading']) != 'undefined') {
				is_loading = option['loading'];
				delete option['loading'];
			}
			return is_loading;
		},
		getParam : function(act_url, act_data, callback, option) {

			if (typeof act_data == 'undefined') act_data = {};
			var returnData = '';
			if (typeof callback != 'function') {
				callback = function(data) {
					returnData = data;
				}
			}

			if (typeof(option) == 'undefined') option = {};
			var is_loading = true;
			if (typeof(option['loading']) != 'undefined') {
				is_loading = option['loading'];
				delete option['loading'];
			}
			var def = {
				'async' : true, 'method' : 'POST', 'datatype' : 'json'
			}
			var opt = $.extend({}, def, option);
			if (typeof opt.async    == 'undefined') opt.async    = true;
			if (typeof opt.method   == 'undefined') opt.method   = 'POST';
			if (typeof opt.datatype == 'undefined') opt.datatype = 'json';
			if (typeof opt.contentType == 'undefined') opt.contentType = 'application/x-www-form-urlencoded; charset=utf-8';
			if (typeof opt.processData == 'undefined') opt.processData = true;
			if (typeof opt.beforeSend  == 'undefined') opt.beforeSend = function() {}
			if (typeof opt.complete  == 'undefined') opt.complete = function() {}

			var cfg = {
				async : opt['async'],
				cache : false,
				type  : opt['method'],
				url   : act_url,
				data  : act_data,
				dataType: opt['datatype'],
				processData : opt['processData'],
				contentType : opt['contentType'],
				beforeSend : function(jqXHR, settings) {
					if (is_loading) Modal.showLoading();
					opt.beforeSend.call(this, jqXHR, settings);
				},
				success: function(data, textStatus, jqXHR) {

					if (!Ajax.checkResponse(data)) return false;
					if (!Ajax.checkLogin(data)) return false;
					if (!Ajax.checkPermission(data)) return false;

					callback(data, textStatus, jqXHR);
				},
				complete : function(jqXHR, textStatus) {
					if (is_loading) Modal.hideLoading();
					opt.complete.call(this, jqXHR, textStatus);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					Ajax.parseError(jqXHR, textStatus, errorThrown);
				}

			};

			return cfg;


			function replaceAll(strTemp, strValue1, strValue2){
				while(1){
					if( strTemp.indexOf(strValue1) != -1 )
						strTemp = strTemp.replace(strValue1, strValue2);
					else
						break;
				}
				return strTemp;
			}

		},
		checkSortable : function(data) {
			if (data == '' || data == null) return false;
			var res_key = ['total', 'page', 'records', 'rows', 'userdata'];
			var key_cnt = 0;
			for (var k in res_key) {
				if (typeof(data[res_key[k]]) != 'undefined') key_cnt++;
			}

			if (key_cnt == res_key.length) return true;
			return false;
		},
		checkResult : function(res) {
			if( res.status != "200"){
				if(res.status =="403"){
					alert('ErrorCode:' + res.err_code + '\nErrorMsg : 권한이 없거나 로그인 정보가 더이상 존재하지 않아 사용할 수 없습니다. 유효한 계정으로 재 로그인하세요' );
				}else {
					alert('ErrorCode:' + res.err_code + '\nErrorMsg : ' + res.err_msg);
				}

				return false;
			}
			return true;
		},
		checkResponse : function(data) {
			if (data == '' || data == null) {
				alert('Response is Nothing!');
				return false;
			}
			//var def_res_key = ['result', 'rescode', 'res_msg', 'err_msg', 'err_dtl'];
			var def_res_key = ['status', 'message', 'timestamp'];
			for (var k in def_res_key) {
				if (typeof(data[def_res_key[k]]) == 'undefined') {
					alert('Check Ajax Response!!');
					return false;
				}
			}
			return true;
		},
		checkLogin : function(data) {
			if (!data.result && data.rescode == 'login') {
				if (globalAjaxLoginStatus) {
					alert('로그인을 하지 않았거나 세션이 종료되었습니다.');
					var redirect = globalConfig['login_url'];
					if (data['is_admin']) redirect = globalConfig['admin_login_url'];
					top.document.location.href = redirect;
				}
				globalAjaxLoginStatus = false;
				return false;
			}
			return true;
		},
		checkPermission : function(data) {
			if (!data.result && data.rescode == 'permission') {
				var msg = '권한이 없습니다.';
				if (typeof(data.err_msg) != 'undefined' && data.err_msg) msg = data.err_msg;
				alert(msg);
				return false;
			}
			return true;
		},
		parseError : function(jqXHR, textStatus, errorThrown) {
			$('.res-execute-result').text('error');
			var status_code = jqXHR.status.toString();
			//var status_msg = jqXHR.message.toString();
			var msg = jqXHR.responseText;
			alert('Ajax Call error! code : ' + status_code )
		}
	}

})

//민규추가 기존파일 : common-pc.js
function echoNull2Blank(str) {
	if (str == null) return '';
	return str;
}

function nvl(str, defaultStr){
	if(typeof str == "undefined" || str == null || str == "")
		str = defaultStr ;
	return str ;
}