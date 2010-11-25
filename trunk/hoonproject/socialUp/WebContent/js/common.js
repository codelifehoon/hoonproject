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
 * �׻� ��â�� Center�� ����ش�. opener�� �����ش�.
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
 * ���ڸ� 3�ڸ� ������ Comma�� ����ش�.
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
 * ���� ��ư�� ���õǾ� �ִ� ���� �����´�.
 * ���� ���õ��� �ʾ����� "null" �� �����ش�.
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
 * ���ڿ�(sSource)�� ���̸� �����ش�.(�ѱ��� 2�ڷ� �ν��Ѵ�)
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
 * �Է��� ���ڿ�(aSource)�� sCompare�� ���ڿ��� ���ԵǾ� �ִ� ���ڷ� �Ǿ� �ִ����� üũ���ش�.
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
 * IsCheckvalidStringType �ʹ� �ٸ��� Ʋ���� True, ������ False�� �����ش�.
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
 * sSource �� ���̰� nMin���� ũ�� nMax���� ������ ���࿡ ������ ��� ��쿡��
 * sName�� ���� ������ �����ش�. nIsConsonant �� True�� "��" �ƴϸ� "��" �� ǥ�����ش�.
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
		cPostL = "��" ;
		cPostN = "��" ;
	}
	else
	{
		cPostL = "��" ;
		cPostN = "��" ;
	}

	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMin +
			"�� �̻�, " + nMax +
			"�� ���Ϸ� �Է��� �ֽʽÿ�.\n���� �Էµ� ���� : " +
			nStrSize + "��" ;
		return false ;
	}

	return true ;
}

/**
 * sSource �� ���̰� nMin���� ũ�� nMax���� ������ ���࿡ ������ ��� ��쿡��
 * sName�� ���� ������ �����ش�. nIsConsonant �� True�� "��" �ƴϸ� "��" �� ǥ�����ش�.
 */
function IsCheckEnglishLength( sSource, sName, nMin, nMax, nIsConsonant )
{
	var nStrSize = GetStringSize( sSource ) ;
	var cPostN, cPostL ;

	if( nIsConsonant )
	{
		cPostL = "��" ;
		cPostN = "��" ;
	}
	else
	{
		cPostL = "��" ;
		cPostN = "��" ;
	}

	if( nStrSize == 0 && nMin > 0 )
	{
		ErrorMessage = sName + cPostL + "  �Է��� �ֽʽÿ�." ;
		return false ;
	}

	if( nMin == 0 && nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMax +
			"�� ���Ϸ� �Է��� �ֽʽÿ�.\n���� �Էµ� ���� : " +
			nStrSize + "��" ;
		return false;
	}
	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " " + nMin +
			"�� �̻�, " + nMax +
			"�� ���Ϸ� �Է��� �ֽʽÿ�.\n���� �Էµ� ���� : " +
			nStrSize + "��" ;
		return false ;
	}

	return true ;
}


/**
 * �ѱ� sSource �� ���̰� nMin���� ũ�� nMax���� ������ ���࿡ ������ ��� ��쿡��
 * sName�� ���� ������ �����ش�. nIsConsonant �� True�� "��" �ƴϸ� "��" �� ǥ�����ش�.
 */
function IsCheckKoreanLength( sSource, sName, nMin, nMax, nIsConsonant )
{
	var nStrSize = GetStringSize( sSource ) ;
	var cPostN, cPostL ;

	if( nIsConsonant )
	{
		cPostL = "��" ;
		cPostN = "��" ;
	}
	else
	{
		cPostL = "��" ;
		cPostN = "��" ;
	}


	if( nStrSize == 0 && nMin > 0 )
	{
		ErrorMessage = sName + cPostL + "  �Է��� �ֽʽÿ�." ;
		return false ;
	}
	if( nMin == 0 && nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " �ѱ� " + Math.floor(nMax/2) +
			"�� ���Ϸ� �Է��� �ֽʽÿ�.\n���� �Էµ� ���� : " +
			nStrSize/2 + "��";
		return false;
	}
	if( nStrSize < nMin || nStrSize > nMax )
	{
		ErrorMessage = sName + cPostN + " �ѱ� " + Math.ceil(nMin/2) +
			"�� �̻�, " + Math.floor(nMax/2) +
			"�� ���Ϸ� �Է��� �ֽʽÿ�.\n���� �Էµ� ���� : " +
			nStrSize/2 + "��";
		return false;
	}

	return true;
}


/**
 * ȸ�� ��?��? Format�� üũ�Ѵ�. �����̸� �ȵǰ�, ���� �ҹ��ڿ� ���ڸ��� �Է¹޴´�.
 * ù���ڴ� ���� �ҹ��ڷ� �ۼ��ؾ� �Ѵ�., admin�̳� guest�� ���̵� �����Ҽ� ����.
 * ȸ�� ���̵� ��å�� ���� �����ؾ���
 */
function IsCheckLoginIDFormat( sLoginID ) {
	/*var nLoginIDSize = GetStringSize( sLoginID ) ;
	if( nUserIDSize == 0 )
	{
		ErrorMessage = "[ȸ�����̵�]�� �� �ۼ��ؾ� �մϴ�." ;
		return false ;
	}
	*/
	if( !IsCheckValidStringType( sLoginID, SALPHA+NUM ) )
	{
		ErrorMessage = "[ȸ�����̵�]�� ���� �ҹ��ڿ� ���ڷ� �����Ǿ�� �ϸ�,\nù ���ڴ� �� ���� �ҹ��ڷ� �ۼ��ؾ� �մϴ�." ;
		return false ;
	}

	if( !IsCheckEnglishLength( sLoginID, "[ȸ�����̵�]", 6, 12, 0 ) )
	{
		return false ;
	}

	if( NUM.indexOf( sLoginID.charAt(0)) >= 0 )
	{
		ErrorMessage = "[ȸ�����̵�]�� ù ���ڴ� �� ���� �ҹ��ڷ� �ۼ��ؾ� �մϴ�." ;
		return false;
	}

	if( sLoginID.substring(0,5)=="admin" || sLoginID.substring(0,5)=="guest" )
	{
		ErrorMessage = "[ȸ�����̵�]�� admin�̳� guest�� ������ �� �����ϴ�." ;
		return false;
	}

	return true ;
}

/**
 * sPassword�� �н����� Format�� �´����� �˻��Ѵ�.
 * �н������ ����, ����, Ư�����ڷ� �����ϸ� 4���� �̻� 16���� ���Ϸ� �ۼ��ؾ� �Ѵ�.
 */
function IsCheckPasswordFormat( sPassword )
{
	if( !IsCheckValidStringType( sPassword, PASSWORD ) )
	{
		ErrorMessage = "[��й�ȣ]�� ����, ����, Ư������(! @ . # , $ % ^ * & _ -)���� �����Ǿ�� �ϸ�\n4���� �̻� 16���� ���Ϸ� �ۼ��ؾ� �մϴ�." ;
		return false ;
	}

	if( !IsCheckEnglishLength( sPassword, "[��й�ȣ]", 6, 12, 0 ) )
	{
		return false ;
	}

	return true;
}


/**
 * �̸� Format�� üũ�ϴ� �κ��̴�.
 * �ѱ۸� �Է��� �����ϴ�.
 */
function IsCheckLoginnameFormat( sSource )
{
	var i ;
	var ch ;

	if( !IsCheckKoreanLength( sSource, "[�̸�]", 2, 12, 1 ) )
	{
		return false ;
	}

	for( i=0 ; i<=sSource.length-1 ; i++ )
	{
		ch = escape( sSource.substring( i, i+1 ) ) ;
		if( ch.length<6 || ch.substring( 2, 6 )<"AC00" || ch.substring( 2, 6 )>"D7AF" )
		{
			ErrorMessage = "[�̸�]�� �ѱ۷θ� �Է��� �����ϸ�\n���� ������ �ʽ��ϴ�." ;
			return false ;
		}
	}

	return true ;
}


/**
 * �̸����� Format�� üũ�Ѵ�.
 */
function IsCheckEmailFormat( sEmail )
{
	var i ;
	var s ;
	if( !IsCheckValidStringType( sEmail, EMAIL ) )
	{
		ErrorMessage = "[�����ּ�]�� �߸��� ���ڰ� �ֽ��ϴ�.";
		return false;
	}

	if( !IsCheckEnglishLength( sEmail, "[�̸���]", 7, 50, 0 ) )
	{
		return false;
	}

	i = sEmail.indexOf('@');
	if( i<=0 || i == sEmail.length-1 )
	{
		ErrorMessage = "[�����ּ�]�� webmaster@kostanet.com ���� ���·� �Է��ϼž� �մϴ�." ;
		return false;
	}

	i = sEmail.indexOf( '.', i+1 ) ;
	if( i<=0 || i == sEmail.length-1 )
	{
	ErrorMessage = "[�����ּ�]�� webmaster@kostanet.com ���� ���·� �Է��ϼž� �մϴ�." ;
		return false;
	}

	/*
	s = sEmail.substring( i+1 ) ;
	if( !IsCheckValidStringType( s, SALPHA ) )
	{
		ErrorMessage = "[�����ּ�]�� webmaster@kostanet.com ���� ���·� �Է��ϼž� �մϴ�." ;
		return false ;
	}
	*/
	return true;
}


/**
 * �ֹ���� Format�� üũ�Ѵ�.
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
		ErrorMessage = "[�ֹε��Ϲ�ȣ �պκ�]�� �� �ۼ��ؾ� �մϴ�." ;
		return -1 ;
	}


	if( !IsCheckValidStringType( sRIDFirst, NUM ) )
	{
		ErrorMessage = "[�ֹε�Ϲ�ȣ �պκ�]�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -1 ;
	}

	if( sRIDFirst.length!=6 ||  nMondth<1 || nMondth>12 || nDay<1 || nDay>31)
	{
		ErrorMessage = "[�ֹε�Ϲ�ȣ �պκ�]�� �߸��Ǿ����ϴ�." ;
		return -1 ;
	}

	nStrSize = GetStringSize( sRIDLast ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[�ֹε��Ϲ�ȣ �޺κ�]�� �� �ۼ��ؾ� �մϴ�." ;
		return -2 ;
	}

	if( !IsCheckValidStringType( sRIDLast, NUM ) )
	{
		ErrorMessage = "[�ֹε�Ϲ�ȣ �޺κ�]�� �߸��� ���ڰ� �ֽ��ϴ�.";
		return -2;
	}

	if( sRIDLast.length!=7 || ( nSex!=1 && nSex!=2 && nSex!=3 && nSex!=4 ) )
	{
		ErrorMessage = "[�ֹε�Ϲ�ȣ �޺κ�]�� �߸��Ǿ����ϴ�.";
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
		ErrorMessage = "��ȿ���� ���� [�ֹε�Ϲ�ȣ]�Դϴ�.";
		return -1;
	}

	return 0;
}


/**
 * �ּҴ� �ʼ� �����̰� �Է� Format�� �°� �ۼ��Ǿ� �ִ��� üũ�Ѵ�.
 * �ڵ� �ּҺκа� �����ּ� �κ����� ������ �ִ�.
 */
function IsCheckHomeaddressFormat( sAddr, sAddrDetail )
{
	var nStrSize = GetStringSize( sAddr ) ;
	// �ڵ� �ּ��Է� �κ�
	if( nStrSize == 0 )
	{
		ErrorMessage = "[�� �ּ�]�� �� �ۼ��ؾ� �մϴ�.\n<�����ȣ>��ư�� �����Ͽ� �Է��� �� [���ּ�]�� �Է��� �ֽʽÿ�." ;
		return -1 ;
	}

	// ���� �ּ��Է� �κ�(����)
	nStrSize = GetStringSize( sAddrDetail ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[���ּ�]�� �� �ۼ��ؾ� �մϴ�." ;
		return -1 ;
	}

	if( IsCheckInvalidStringType( sAddrDetail, "\"<>" ) )
	{
		ErrorMessage = "[���ּ�]�� �߸��� ����  <  >  Ȥ��  \"  �� �ֽ��ϴ�." ;
		return -2 ;
	}

	if ( !IsCheckKoreanLength( sAddrDetail, "[���ּ�]", 1, 100, 0 ) )
	{
		return -2 ;
	}

	return 0 ;
}


/**
 * �ּҸ� �Է��ϴµ�. ��ּ������� sName�� �־��ְ�, sNameDetail���� ������ ���Է��ؾ�
 * �ϴ����� �־��ش�.
 */
function IsCheckAddressFormat( sName, sNameDetail, sAddr, sAddrDetail )
{
	var nStrSize = GetStringSize( sAddrDetail ) ;
	// �ڵ� �ּ��Է� �κ�
	if( nStrSize > 0 )
	{
		nStrSize = GetStringSize( sAddr ) ;
		if( nStrSize == 0 )
		{
			ErrorMessage = sName + "�� �� �ۼ��ؾ� �մϴ�.\n<�����ȣ>��ư�� �����Ͽ� �Է��� �� " + sNameDetail + "�� �Է��� �ֽʽÿ�." ;
			return -1 ;
		}

		if( IsCheckInvalidStringType( sAddrDetail, "\"<>" ) )
		{
			ErrorMessage = sNameDetail + "�� �߸��� ����  <  >  Ȥ��  \"  �� �ֽ��ϴ�." ;
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
 * ��ȭ��ȣ�� üũ�Ѵ�.
 *
 */
function IsCheckHomephoneFormat( nhome_tel1, nhome_tel2, nhome_tel3 )
{
	var nStrSize = GetStringSize( nhome_tel1 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �� �ۼ��ؾ� �մϴ�." ;
		return -1 ;
	}
	if( !IsCheckValidStringType( nhome_tel1, NUM ) )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -1 ;
	}

	nStrSize = GetStringSize( nhome_tel2 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �� �ۼ��ؾ� �մϴ�." ;
		return -2 ;
	}
	if(!IsCheckValidStringType( nhome_tel2, NUM ) )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -2 ;
	}

	nStrSize = GetStringSize( nhome_tel3 ) ;
	if( nStrSize == 0 )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �� �ۼ��ؾ� �մϴ�." ;
		return -3 ;
	}
	if( !IsCheckValidStringType( nhome_tel3, NUM ) )
	{
		ErrorMessage = "[��ȭ��ȣ]�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -3 ;
	}

	if ( !IsCheckEnglishLength( nhome_tel1, "[��ȭ��ȣ]", 1, 4, 0 ) )
	{
		return -1 ;
	}
	if ( !IsCheckEnglishLength( nhome_tel2, "[��ȭ��ȣ]", 1, 4, 0 ) )
	{
		return -2 ;
	}
	if ( !IsCheckEnglishLength( nhome_tel3, "[��ȭ��ȣ]", 1, 4, 0 ) )
	{
		return -3 ;
	}

	return 0;
}


/*
 * ��ȭ��ȣ�� üũ�Ѵ�. 3���� ������ üũ�Ѵ�.
 */
function IsCheckPhoneFormat( sName, nTel1, nTel2, nTel3 )
{
	var nStrSize ;

	if( !IsCheckValidStringType( nTel1, NUM ) )
	{
		ErrorMessage = sName + "�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -1 ;
	}

	if(!IsCheckValidStringType( nTel2, NUM ) )
	{
		ErrorMessage = sName + "�� �߸��� ���ڰ� �ֽ��ϴ�." ;
		return -2 ;
	}

	if( !IsCheckValidStringType( nTel3, NUM ) )
	{
		ErrorMessage = sName + "�� �߸��� ���ڰ� �ֽ��ϴ�." ;
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
 * TR �� ���콺 ���� �� �ƿ� �Ǿ������� ����
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
 * Radio ���� ����
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
 * ���ڿ� ��ȯ
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
* Ajaxȣ��
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
