// ! form input check
var NUM = "0123456789";
var SALPHA = "abcdefghijklmnopqrstuvwxyz";
var ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+SALPHA;
var EMAIL = "!#$%&*+-./=?@^_`{|}"+NUM+ALPHA;
var PASSWORD = "!@.#,$%^*&_-" + ALPHA + NUM;
var ErrorMessage = "";

/*
function alert( args, height, width)
{
	var sHeight;
	var sWidth;

	var args_length = alert.arguments.length;

	if(args_length < 3)
	{
		sHeight = 212;
		sWidth	= 400;
	} else
	{
		sHeight = height;
		sWidth	= width;
	}
	window.showModalDialog("/backoffice/Message/MessageAlert.jsp",args,"dialogHeight: "+ sHeight +"px; dialogWidth: "+ sWidth +"px; edge: Raised; center: Yes; help: No; resizable: No; status: No; scroll: No;");
}
*/

/**
 * 항상 새창을 Center로 띄워준다. opener를 돌려준다.
 */

function launchCenter(url, name,  width, height)
{
	var str = "height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;

	if (window.screen)
	{
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;

		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;

		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
	}

	return window.open(url, name, str);
}


function launchCenter(url, name, width,height, scroll)
{
	var str = "height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;
	str += ",status=no,scrollbars=" + scroll;

	if (window.screen)
	{
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;

		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;

		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
	}

	return window.open(url, name, str);
}

/**
 * 숫자를 3자리 단위로 Comma를 찍어준다.
 */

function getCommaSplit(srcNumber)
{
	var txtNumber = '' + srcNumber;

	if (isNaN(txtNumber) || txtNumber == "")
	{
		return srcNumber;
	}
	else
	{
		var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
		var arrNumber = txtNumber.split('.');
		arrNumber[0] += '.';

		do
		{
			arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
		} while (rxSplit.test(arrNumber[0]));

		if (arrNumber.length > 1)
		{
			return arrNumber.join('');
		}
		else
		{
			return arrNumber[0].split('.')[0];
		}
	}
}


/**
 * 라디오 버튼중 선택되어 있는 값을 가져온다.
 * 만약 선택되지 않았으면 "null" 을 돌려준다.
 */

function radioValue( oRadio )
{
    var value = "null";

    for(var i=0;i<oRadio.length; i++)
    {
        if (oRadio[i].checked == true)
        {
        	value = oRadio[i].value;
        }
    }

	return value;
}



/**
 * 문자열(sSource)의 길이를 구해준다.(한글은 2자로 인식한다)
 */
function GetStringSize ( sSource )
{
	var i;
	var len = 0;
	for( i=0 ; i<sSource.length ; i++ )
	{
		if( sSource.charCodeAt(i) > 255 )
		{
			len += 2;
		}
		else
		{
			len ++;
		}
	}
	return len;
}


/**
 * 입력한 문자열(aSource)가 sCompare의 문자열에 포함되어 있는 문자로 되어 있는지를 체크해준다.
 */
function IsCheckValidStringType( sSource, sCompare )
{
	var i ;
	for( i=0; i< sSource.length; i++ )
	{
		if( sCompare.indexOf( sSource.substring( i, i+1 ) ) < 0)
		{
			return false ;
		}
	}

	return true ;
}


/**
 * IsCheckvalidStringType 와는 다르게 틀리면 True, 맞으면 False를 돌려준다.
 */
function IsCheckInvalidStringType( sSource, sCompare )
{
	var i ;
	for( i=0; i<sSource.length; i++ )
	{
		if( sCompare.indexOf( sSource.substring( i, i+1 ) ) >= 0 )
		{
			return true ;
		}
	}

	return false ;
}


/**
 * sSource 의 길이가 nMin보다 크고 nMax보다 작으며 만약에 범위를 벗어날 경우에는
 * sName에 대한 오류로 보여준다. nIsConsonant 이 True면 "을" 아니면 "를" 로 표시해준다.
 */
function IsCheckStringLengthRange( sSource, sName, nMin, nMax, nIsConsonant )
{
	var nStrSize = GetStringSize( sSource ) ;
	var cPostN, cPostL ;

	if( nStrSize == 0 )
	{
		return true ;
	}

	if( nIsConsonant )
	{
		cPostL = "을" ;
		cPostN = "은" ;
	}
	else
	{
		cPostL = "를" ;
		cPostN = "는" ;
	}

	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMin +
			"자 이상, " + nMax +
			"자 이하로 입력해 주십시오.\n현재 입력된 길이 : " +
			nStrSize + "자" ;
		return false ;
	}

	return true ;
}

/**
 * sSource 의 길이가 nMin보다 크고 nMax보다 작으며 만약에 범위를 벗어날 경우에는
 * sName에 대한 오류로 보여준다. nIsConsonant 이 True면 "을" 아니면 "를" 로 표시해준다.
 */
function IsCheckEnglishLength( sSource, sName, nMin, nMax, nIsConsonant )
{
	var nStrSize = GetStringSize( sSource ) ;
	var cPostN, cPostL ;

	if( nIsConsonant )
	{
		cPostL = "을" ;
		cPostN = "은" ;
	}
	else
	{
		cPostL = "를" ;
		cPostN = "는" ;
	}

	if( nStrSize == 0 && nMin > 0 )
	{
		ErrorMessage = sName + cPostL + "  입력해 주십시오." ;
		return false ;
	}

	if( nMin == 0 && nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMax +
			"자 이하로 입력해 주십시오.\n현재 입력된 길이 : " +
			nStrSize + "자" ;
		return false;
	}
	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMin +
			"자 이상, " + nMax +
			"자 이하로 입력해 주십시오.\n현재 입력된 길이 : " +
			nStrSize + "자" ;
		return false ;
	}

	return true ;
}


/**
 * 한글 sSource 의 길이가 nMin보다 크고 nMax보다 작으며 만약에 범위를 벗어날 경우에는
 * sName에 대한 오류로 보여준다. nIsConsonant 이 True면 "을" 아니면 "를" 로 표시해준다.
 */
function IsCheckKoreanLength( sSource, sName, nMin, nMax, nIsConsonant )
{
	var nStrSize = GetStringSize( sSource ) ;
	var cPostN, cPostL ;

	if( nIsConsonant )
	{
		cPostL = "을" ;
		cPostN = "은" ;
	}
	else
	{
		cPostL = "를" ;
		cPostN = "는" ;
	}


	if( nStrSize == 0 && nMin > 0 )
	{
		ErrorMessage = sName + cPostL + "  입력해 주십시오." ;
		return false ;
	}
	if( nMin == 0 && nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " 한글 " + Math.floor(nMax/2) +
			"자 이하로 입력해 주십시오.\n현재 입력된 길이 : " +
			nStrSize/2 + "자";
		return false;
	}
	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " 한글 " + Math.ceil(nMin/2) +
			"자 이상, " + Math.floor(nMax/2) +
			"자 이하로 입력해 주십시오.\n현재 입력된 길이 : " +
			nStrSize/2 + "자";
		return false;
	}

	return true;
}


/**
 * 회원 아?事? Format을 체크한다. 공백이면 안되고, 영문 소문자와 숫자만을 입력받는다.
 * 첫글자는 영문 소문자로 작성해야 한다., admin이나 guest로 아이디를 시작할수 없다.
 * 회원 아이디 정책에 따라 변경해야함
 */
function IsCheckLoginIDFormat( sLoginID ) {
	/*var nLoginIDSize = GetStringSize( sLoginID ) ;
	if( nUserIDSize == 0 )
	{
		ErrorMessage = "[회원아이디]는 꼭 작성해야 합니다." ;
		return false ;
	}
	*/
	if( !IsCheckValidStringType( sLoginID, SALPHA+NUM ) )
	{
		ErrorMessage = "[회원아이디]는 영문 소문자와 숫자로 구성되어야 하며,\n첫 글자는 꼭 영문 소문자로 작성해야 합니다." ;
		return false ;
	}

	if( !IsCheckEnglishLength( sLoginID, "[회원아이디]", 6, 12, 0 ) )
	{
		return false ;
	}

	if( NUM.indexOf( sLoginID.charAt(0)) >= 0 )
	{
		ErrorMessage = "[회원아이디]의 첫 글자는 꼭 영문 소문자로 작성해야 합니다." ;
		return false;
	}

	if( sLoginID.substring(0,5)=="admin" || sLoginID.substring(0,5)=="guest" )
	{
		ErrorMessage = "[회원아이디]는 admin이나 guest로 시작할 수 없습니다." ;
		return false;
	}

	return true ;
}

/**
 * sPassword가 패스워드 Format에 맞는지를 검사한다.
 * 패스워드는 영문, 숫자, 특수문자로 구성하며 4글자 이상 16글자 이하로 작성해야 한다.
 */
function IsCheckPasswordFormat( sPassword )
{
	if( !IsCheckValidStringType( sPassword, PASSWORD ) )
	{
		ErrorMessage = "[비밀번호]는 영문, 숫자, 특수문자(! @ . # , $ % ^ * & _ -)으로 구성되어야 하며\n4글자 이상 16글자 이하로 작성해야 합니다." ;
		return false ;
	}

	if( !IsCheckEnglishLength( sPassword, "[비밀번호]", 6, 12, 0 ) )
	{
		return false ;
	}

	return true;
}


/**
 * 이름 Format을 체크하는 부분이다.
 * 한글만 입력이 가능하다.
 */
function IsCheckLoginnameFormat( sSource )
{
	var i ;
	var ch ;

	if( !IsCheckKoreanLength( sSource, "[이름]", 2, 12, 1 ) )
	{
		return false ;
	}

	for( i=0 ; i<=sSource.length-1 ; i++ )
	{
		ch = escape( sSource.substring( i, i+1 ) ) ;
		if( ch.length<6 || ch.substring( 2, 6 )<"AC00" || ch.substring( 2, 6 )>"D7AF" )
		{
			ErrorMessage = "[이름]은 한글로만 입력이 가능하며\n띄어쓰는 허용되지 않습니다." ;
			return false ;
		}
	}

	return true ;
}


/**
 * 이메일을 Format을 체크한다.
 */
function IsCheckEmailFormat( sEmail )
{
	var i ;
	var s ;
	if( !IsCheckValidStringType( sEmail, EMAIL ) )
	{
		ErrorMessage = "[메일주소]에 잘못된 문자가 있습니다.";
		return false;
	}

	if( !IsCheckEnglishLength( sEmail, "[이메일]", 7, 50, 0 ) )
	{
		return false;
	}

	i = sEmail.indexOf('@');
	if( i<=0 || i == sEmail.length-1 )
	{
		ErrorMessage = "[메일주소]은 webmaster@kostanet.com 등의 형태로 입력하셔야 합니다." ;
		return false;
	}

	i = sEmail.indexOf( '.', i+1 ) ;
	if( i<=0 || i == sEmail.length-1 )
	{
	ErrorMessage = "[메일주소]은 webmaster@kostanet.com 등의 형태로 입력하셔야 합니다." ;
		return false;
	}

	/*
	s = sEmail.substring( i+1 ) ;
	if( !IsCheckValidStringType( s, SALPHA ) )
	{
		ErrorMessage = "[메일주소]은 webmaster@kostanet.com 등의 형태로 입력하셔야 합니다." ;
		return false ;
	}
	*/
	return true;
}


/**
 * 주문등록 Format을 체크한다.
 */
function IsCheckResidentFormat( sRIDFirst, sRIDLast )
{
	var chk		= 0 ;

	var nYear	= sRIDFirst.substring(0,2) ;
	var nMondth = sRIDFirst.substring(2,4) ;
	var nDay	= sRIDFirst.substring(4,6) ;

	var nSex	= sRIDLast.charAt(0) ;

	var nStrSize = GetStringSize( sRIDFirst ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[주민동록번호 앞부분]은 꼭 작성해야 합니다." ;
		return -1 ;
	}


	if( !IsCheckValidStringType( sRIDFirst, NUM ) )
	{
		ErrorMessage = "[주민등록번호 앞부분]에 잘못된 문자가 있습니다." ;
		return -1 ;
	}

	if( sRIDFirst.length!=6 ||  nMondth<1 || nMondth>12 || nDay<1 || nDay>31)
	{
		ErrorMessage = "[주민등록번호 앞부분]이 잘못되었습니다." ;
		return -1 ;
	}

	nStrSize = GetStringSize( sRIDLast ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[주민동록번호 뒷부분]은 꼭 작성해야 합니다." ;
		return -2 ;
	}

	if( !IsCheckValidStringType( sRIDLast, NUM ) )
	{
		ErrorMessage = "[주민등록번호 뒷부분]에 잘못된 문자가 있습니다.";
		return -2;
	}

	if( sRIDLast.length!=7 || ( nSex!=1 && nSex!=2 && nSex!=3 && nSex!=4 ) )
	{
		ErrorMessage = "[주민등록번호 뒷부분]이 잘못되었습니다.";
		return -2;
	}

	var i;

	// a1*2+a2*3+a3*4+a4*5+a5*6+a6*7
	for( i=0; i<6; i++ )
	{
		chk += ( ( i+2 ) * parseInt( sRIDFirst.charAt( i ) ) ) ;
	}

	// b1*8+b2*9+b3*2+b4*3+b5*4+b6*5
	for( i=6; i<12; i++ )
	{
		chk += ( ( i%8+2 ) * parseInt( sRIDLast.charAt( i-6 ) ) ) ;
	}

	chk = 11 - ( chk%11 ) ;
	chk %= 10 ;

	if( chk != parseInt( sRIDLast.charAt( 6 ) ) )
	{
		ErrorMessage = "유효하지 않은 [주민등록번호]입니다.";
		return -1;
	}

	return 0;
}


/**
 * 주소는 필수 사항이고 입력 Format에 맞게 작성되어 있는지 체크한다.
 * 자동 주소부분과 수동주소 부분으로 나눠져 있다.
 */
function IsCheckHomeaddressFormat( sAddr, sAddrDetail )
{
	var nStrSize = GetStringSize( sAddr ) ;
	// 자동 주소입력 부분
	if( nStrSize == 0 )
	{
		ErrorMessage = "[집 주소]를 꼭 작성해야 합니다.\n<우편번호>버튼을 선택하여 입력한 후 [상세주소]를 입력해 주십시오." ;
		return -1 ;
	}

	// 수동 주소입력 부분(번지)
	nStrSize = GetStringSize( sAddrDetail ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[상세주소]를 꼭 작성해야 합니다." ;
		return -1 ;
	}

	if( IsCheckInvalidStringType( sAddrDetail, "\"<>" ) )
	{
		ErrorMessage = "[상세주소]에 잘못된 문자  <  >  혹은  \"  가 있습니다." ;
		return -2 ;
	}

	if ( !IsCheckKoreanLength( sAddrDetail, "[상세주소]", 1, 100, 0 ) )
	{
		return -2 ;
	}

	return 0 ;
}


/**
 * 주소를 입력하는데. 어떤주소인지를 sName에 넣어주고, sNameDetail에는 무엇을 꼭입력해야
 * 하는지를 넣어준다.
 */
function IsCheckAddressFormat( sName, sNameDetail, sAddr, sAddrDetail )
{
	var nStrSize = GetStringSize( sAddrDetail ) ;
	// 자동 주소입력 부분
	if( nStrSize > 0 )
	{
		nStrSize = GetStringSize( sAddr ) ;
		if( nStrSize == 0 )
		{
			ErrorMessage = sName + "를 꼭 작성해야 합니다.\n<우편번호>버튼을 선택하여 입력한 후 " + sNameDetail + "를 입력해 주십시오." ;
			return -1 ;
		}

		if( IsCheckInvalidStringType( sAddrDetail, "\"<>" ) )
		{
			ErrorMessage = sNameDetail + "에 잘못된 문자  <  >  혹은  \"  가 있습니다." ;
			return -2 ;
		}

		if ( !IsCheckKoreanLength( sAddrDetail, sNameDetail, 1, 100, 0 ) )
		{
			return -2 ;
		}
	}

	return 0 ;
}

/**
 * 전화번호를 체크한다.
 *
 */
function IsCheckHomephoneFormat( nhome_tel1, nhome_tel2, nhome_tel3 )
{
	var nStrSize = GetStringSize( nhome_tel1 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[전화번호]를 꼭 작성해야 합니다." ;
		return -1 ;
	}
	if( !IsCheckValidStringType( nhome_tel1, NUM ) )
	{
		ErrorMessage = "[전화번호]에 잘못된 문자가 있습니다." ;
		return -1 ;
	}

	nStrSize = GetStringSize( nhome_tel2 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[전화번호]를 꼭 작성해야 합니다." ;
		return -2 ;
	}
	if(!IsCheckValidStringType( nhome_tel2, NUM ) )
	{
		ErrorMessage = "[전화번호]에 잘못된 문자가 있습니다." ;
		return -2 ;
	}

	nStrSize = GetStringSize( nhome_tel3 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[전화번호]를 꼭 작성해야 합니다." ;
		return -3 ;
	}
	if( !IsCheckValidStringType( nhome_tel3, NUM ) )
	{
		ErrorMessage = "[전화번호]에 잘못된 문자가 있습니다." ;
		return -3 ;
	}

	if ( !IsCheckEnglishLength( nhome_tel1, "[전화번호]", 1, 4, 0 ) )
	{
		return -1 ;
	}
	if ( !IsCheckEnglishLength( nhome_tel2, "[전화번호]", 1, 4, 0 ) )
	{
		return -2 ;
	}
	if ( !IsCheckEnglishLength( nhome_tel3, "[전화번호]", 1, 4, 0 ) )
	{
		return -3 ;
	}

	return 0;
}


/*
 * 전화번호를 체크한다. 3개를 나눠서 체크한다.
 */
function IsCheckPhoneFormat( sName, nTel1, nTel2, nTel3 )
{
	var nStrSize ;

	if( !IsCheckValidStringType( nTel1, NUM ) )
	{
		ErrorMessage = sName + "에 잘못된 문자가 있습니다." ;
		return -1 ;
	}

	if(!IsCheckValidStringType( nTel2, NUM ) )
	{
		ErrorMessage = sName + "에 잘못된 문자가 있습니다." ;
		return -2 ;
	}

	if( !IsCheckValidStringType( nTel3, NUM ) )
	{
		ErrorMessage = sName + "에 잘못된 문자가 있습니다." ;
		return -3 ;
	}

	nStrSize = GetStringSize( nTel1 ) ;
	if( nStrSize > 0 )
	{
		if ( !IsCheckStringLengthRange( nTel1, sName, 1, 4, 0 ) )
		{
			return -1 ;
		}
	}
	nStrSize = GetStringSize( nTel2 ) ;
	if( nStrSize > 0 )
	{
		if ( !IsCheckStringLengthRange( nTel2, sName, 1, 4, 0 ) )
		{
			return -2 ;
		}
	}
	nStrSize = GetStringSize( nTel3 ) ;
	if( nStrSize > 0 )
	{
		if ( !IsCheckStringLengthRange( nTel3, sName, 1, 4, 0 ) )
		{
			return -1 ;
		}
	}

	return 0;
}

/*
 * TR 에 마우스 오버 및 아웃 되었을때의 색깔
 */
function activeArt(tg)
{
tg.className = 'ResultTitle';
tg.style.cursor='hand';
}

function deactiveArt(tg)
{
tg.className = 'ResultContent';
tg.style.cursor='';
}

/*
 * Radio 선택 해줌
 */
function choiceRadio(obj, value)
{
	for( i=0; i < obj.length; i++ )
	{
		if( obj[i].value == value )
		{
			obj[i].checked = true;
		}
	}
}

/*
 * 문자열 변환
 */
function replace(str,s,d){
	var i=0;

	while(i > -1){
	 i = str.indexOf(s);
	 str = str.substr(0,i) + d + str.substr(i+1,str.length);
	}

 	return str;
}

/*
* Ajax호출
*/
function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");

			}catch(e1){
				return null;
			}
		}
	}else if (window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else{
		return null;
	}
}

function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/gi, "");
}

/*
 * 
 * document.getElementById 에 대한 단축어로 사용한다
 * */
function getEleById(id)
{
	return document.getElementById(id);
}
