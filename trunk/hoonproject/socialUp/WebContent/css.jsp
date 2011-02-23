<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<style type="text/css">
@charset "utf-8"; 
/* Jeong Chan Myeong(dece24@gmail.com) */

/* Button Reset */
.button,
.button a,
.button button,
.button input { position:relative; margin:0; display:-moz-inline-stack; display:inline-block; text-decoration:none !important; border:0; font-size:15px; font-family:Tahoma, Sans-serif; white-space:nowrap; background:url(../img/buttonWhite.gif) no-repeat; overflow:visible; color:#333;}
.button { height:23px; padding:0; vertical-align:middle; margin-right:2px; background-position:left top;}
.button a,
.button button,
.button input{ left:2px; vertical-align:top; cursor:pointer;}

/* Button Size */
.button.large { height:29px; background-position:left -30px; }
.button.xLarge { height:35px; background-position:left -65px; }
.button.small { height:18px; background-position:left -107px; }
.button a,
.button button,
.button input { height:23px; padding:0 10px 0 8px; font-size:15px; line-height:23px; background-position:right top;}
.button.large a,
.button.large button,
.button.large input { height:30px; padding:0 10px 0 8px; font-size:16px; line-height:29px; background-position:right -30px;}
.button.xLarge a,
.button.xLarge button,
.button.xLarge input { height:35px; padding:0 10px 0 8px; font-size:20px; line-height:35px; background-position:right -65px;}
.button.small a,
.button.small button,
.button.small input { height:18px; padding:0 6px 0 4px; font-size:11px; line-height:18px; background-position:right -107px;}

/* Button Text Color */
.button *:hover,
.button *:active,
.button *:focus{ color:#690;}

.button.blue,
.button.blue a,
.button.blue button,
.button.blue input { background-image:url(buttonBlue.gif); color:#fff;}
.button.blue *:hover,
.button.blue *:active,
.button.blue *:focus{ color:#ff0;}

P { font: 15px Tahoma, Helvetica,돋움,Arial, Sans-Serif;}

A:link, a:visited { text-decoration: none; color: #2276BB; } 
A[href] { font: 15px Tahoma, Helvetica,돋움,Arial, Sans-Serif}
A:hover { font: 15px ; color:#2276BB;text-decoration: underline} 


h2 {
	font: 20px Tahoma, Helvetica,돋움,Arial, Sans-Serif;
	font-weight: bold;
	text-align: left;
    }


/* Background Color */
.tt1 {
	font: 20px Tahoma, Helvetica,돋움,Arial, Sans-Serif;
	font-weight: bold;
	text-align: left;
	color:#fff;
	background-color : #0033CC;
	border: 1px solid #008000;
	width: 300px;
}

.tt2 {
	font: 17px Tahoma, Helvetica,돋움,Arial, Sans-Serif;
	font-weight: bold;
	text-align: left;
	color:#fff;
	background-color : #0033CC;
	border: 1px solid #008000;
	width: 300px;
}
    
</style>

</head>
 
<body>


<P class="tt1">일반문자</P>
<P class="tt2">LINE21</P>
<P>일반문자</P>
<h2>LINE21</h2>
<h2>가나다라마</h2>
<A href="">하이퍼링크문자</A>
	<table border="1"> 
			<caption>Blue Buttons</caption> 
			<thead> 
				<tr> 
					<th scope="col">Element / Size</th> 
					<th scope="col">small</th> 
					<th scope="col">default</th> 
					<th scope="col">large</th> 
					<th scope="col">xLarge</th> 
				</tr> 
			</thead> 
			<tbody> 
				<tr> 
					<th scope="row">a</th> 
					<td><span class="button blue small"><a href="#">anchor</a></span></td> 
					<td><span class="button blue"><a href="#">anchor</a></span></td> 
					<td><span class="button blue large"><a href="#">anchor</a></span></td> 
					<td><span class="button blue xLarge"><a href="#">anchor</a></span></td> 
				</tr> 
				<tr> 
					<th scope="row">button</th> 
					<td><span class="button blue small"><button type="button">button</button></span></td> 
					<td><span class="button blue"><button type="button">button</button></span></td> 
					<td><span class="button blue large"><button type="button">button</button></span></td> 
					<td><span class="button blue xLarge"><button type="button">가나다라마바사</button></span></td> 
				</tr> 
				<tr> 
					<th scope="row">input</th> 
					<td><span class="button blue small"><input type="submit" value="input" /></span></td> 
					<td><span class="button blue"><input type="submit" value="input" /></span></td> 
					<td><span class="button blue large"><input type="submit" value="input" /></span></td> 
					<td><span class="button blue xLarge"><input type="submit" value="input" /></span></td> 
				</tr> 
			</tbody> 
		</table> 

</body>

