<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Meet Taiwan</title>
<!-- old -->
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/intro2_zh_TW.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/base.css" />

<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/jquery-ui-1.7.2.custom.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/redmond.datepick.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/stripeMe.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/forpage.css" />



<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/index_cha.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/mt_ch_2.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/indexintro_zh_TW.css" />
<link type="text/css" rel="stylesheet" media="all" href="http://www.meettaiwan.com/css/2010css/jqModal_zh_TW.css" />

<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jquery-1.4.1.js" charset="utf-8"></script>
<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jquery-ui-1.7.1.custom.min.js" charset="utf-8"></script>
<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jquery.form.js"></script>


<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/select-chain.js" charset="utf-8"></script>
<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jquery.datepick.min.js"></script>
<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jquery.autocomplete.min.js" charset="utf-8"></script>
<script language="JavaScript" type="text/javascript"
    src="http://www.meettaiwan.com/js/jqModal.js" charset="utf-8"></script>

<script type="text/javascript">
      var base_path = "/";
      var path_to_theme = "/";
      var ie6 = false;
      var ads = {};
</script>
<script language="javascript">
        $(document).ready(
                function(){
                    if($("#news_container .news_image img").length==0){
                        $("#news_container .main_news").addClass("no_image");
                    }

                    function textBoxBackground(boxClassName, bgBoxClassName){
                        $("."+boxClassName).each(
                            function(){
                                var div=$("<div class='"+bgBoxClassName+"'><\/div>");
                                $(this).parent().append(div);
                                var top=$(this).position()['top'];
                                var left=$(this).position()['left'];
                                div.css({top: top-3, left: left-3});
                            }
                        );
                    }
                    textBoxBackground("short_text_input", "short_box_container");
                    textBoxBackground("text_input", "box_container");
                    textBoxBackground("long_text_input", "long_box_container");
                    /*
                    $("input.short_text_input").each(
                        function(){
                            $(this).wrap("<span class='short_box_container'><\/span>");
                        }
                    );

                    $("input.long_text_input").each(
                        function(){
                            $(this).wrap("<div class='long_box_container'><\/div>");

                        }
                    );
                    */
                    $(".saveButton").click(function() {
                        return confirm("確定要儲存?");
                    });
                    $(".cancelButton").click(function() {
                        return confirm("確定要取消目前操作?");
                    });
                }
            );
        function activate(oid) {
            if (confirm("確定要儲存?")) {
             $("#oid").val(oid);
             $("#action").attr("name","method:ajaxActivate");
             $("#actionForm").submit();
            }
        }
        function inactivate(oid) {
            if (confirm("確定要儲存?")) {
             $("#oid").val(oid);
             $("#action").attr("name","method:ajaxInactivate");
             $("#actionForm").submit();
            }
        }
function update(id) {
	window.location = 'project.htm?act=update&id=' + id;
}
        </script>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>


    <script type="text/javascript" src="http://www.meettaiwan.com/js/slidelanguage_zh_TW.js"></script>

<!--page use-->
<script type="text/javascript" src="http://www.meettaiwan.com/js/menu.js"></script>
<script type="text/javascript">
<!--//--><![CDATA[//><!--
/**
 * jQuery.extend(Drupal.settings, { "basePath": "/", "lightbox2": { "rtl": 0, "file_path": "/(\\w\\w/)sites/default/files", "default_image": "/sites/all/modules/lightbox2/images/brokenimage.jpg", "border_size": 10, "font_color": "000", "box_color": "fff", "top_position": "", "overlay_opacity": "0.8", "overlay_color": "000", "disable_close_click": 1, "resize_sequence": 0, "resize_speed": 400, "fade_in_speed": 400, "slide_down_speed": 600, "use_alt_layout": 0, "disable_resize": 0, "disable_zoom": 0, "force_show_nav": 0, "show_caption": true, "loop_items": 0, "node_link_text": "View Image Details", "node_link_target": 0, "image_count": "Image !current of !total", "video_count": "Video !current of !total", "page_count": "Page !current of !total", "lite_press_x_close": "press \x3ca href=\"#\" onclick=\"hideLightbox(); return FALSE;\"\x3e\x3ckbd\x3ex\x3c/kbd\x3e\x3c/a\x3e to close", "download_link_text": "", "enable_login": false, "enable_contact": false, "keys_close": "c x 27", "keys_previous": "p 37", "keys_next": "n 39", "keys_zoom": "z", "keys_play_pause": "32", "display_image_size": "original", "image_node_sizes": "()", "trigger_lightbox_classes": "", "trigger_lightbox_group_classes": "", "trigger_slideshow_classes": "", "trigger_lightframe_classes": "", "trigger_lightframe_group_classes": "", "custom_class_handler": 0, "custom_trigger_classes": "", "disable_for_gallery_lists": true, "disable_for_acidfree_gallery_lists": true, "enable_acidfree_videos": true, "slideshow_interval": 5000, "slideshow_automatic_start": true, "slideshow_automatic_exit": true, "show_play_pause": true, "pause_on_next_click": false, "pause_on_previous_click": true, "loop_slides": false, "iframe_width": 600, "iframe_height": 400, "iframe_border": 1, "enable_video": 1, "flvPlayer": "/", "flvFlashvars": "" }, "session_favorites": { "path": "favourites" } });
 */
//--><!]]>
</script>
<!--page use end-->
<script type="text/javascript" src="Scripts/swfobject.js"></script>

<!-- old -->

<link href="http://www.meettaiwan.com/css_2012/2012_reset_zh_TW.css" rel="stylesheet" type="text/css" />
<link href="http://www.meettaiwan.com/css_2012/2012_page_zh_TW.css" rel="stylesheet" type="text/css" />
<link href="http://www.meettaiwan.com/css_2012/2012_header_pic_zh_TW.css" rel="stylesheet" type="text/css" />

<!--MENU效果 開始-->
<script type="text/javascript" src="http://www.meettaiwan.com/js_2012/csshorizontalmenu.js"></script>
<!--MENU效果 結束-->

<script type="text/javascript">
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>
</head>

<body onload="MM_preloadImages('http://www.meettaiwan.com/images_2012/menu/S001_01_zh_TW_on.png','http://www.meettaiwan.com/images_2012/menu/S001_02_zh_TW_on.png','http://www.meettaiwan.com/images_2012/menu/S001_03_zh_TW_on.png','http://www.meettaiwan.com/images_2012/menu/S001_06_zh_TW_on.png','http://www.meettaiwan.com/images_2012/menu/S001_08_zh_TW_on.png','http://www.meettaiwan.com/images_2012/menu/S001_09_zh_TW_on.png')" style="background:#CCC;">
<div align="center">
	






 


<script type="text/javascript">
		function searchType1(){
	    var qString = $("#queryString1").val();
	
	    if(qString.length < 2){
	        alert('請輸入兩個字以上的搜尋字串');
	        return false;
	    }else{
	        $("#textSearchForm").submit();
	    }
		}
</script>

<!--HEADER BOX 開始-->
<div id="tm_pageheader_box">
	
<div id="tm_header_logo_box">
        	<!--LOGO 開始-->
            	<div id="tm_logo_box" style="*margin-left:-231px !important;"><img src="http://www.meettaiwan.com/images_2012/page_logo.png" alt="Meet Taiwan Logo" width="259" height="139" border="0" /></div>
            <!--LOGO 結束-->
            
            <!--Social & Search 開始-->
<div id="tm_social_box">
                	<ol id="tm_social_list">
                   	  <li><img src="http://www.meettaiwan.com/images_2012/social/facebook.png" width="20" height="20" align="absmiddle" /></li>
                      <li><img src="http://www.meettaiwan.com/images_2012/social/youtube.png" width="20" height="20" align="absmiddle" /></li>
                      <li><img src="http://www.meettaiwan.com/images_2012/social/twitter.png" width="20" height="20" align="absmiddle" /></li>
                      
                      
                      	<li><img src="http://www.meettaiwan.com/images_2012/icon/top_arrow.png" width="12" height="12" align="absmiddle" />正體中文</li>
                      
                      	<li><img src="http://www.meettaiwan.com/images_2012/icon/top_arrow.png" width="12" height="12" align="absmiddle" />English</li>
                      
                      	<li><img src="http://www.meettaiwan.com/images_2012/icon/top_arrow.png" width="12" height="12" align="absmiddle" />日本語</li>
                      
                      	<li><img src="http://www.meettaiwan.com/images_2012/icon/top_arrow.png" width="12" height="12" align="absmiddle" />简体中文</li>
                      
                      
                      <li id="tm_search_box">
    						<input id="queryString1" name="queryString" type="text" class="tm_search_input" />
    						<img src="http://www.meettaiwan.com/images_2012/icon/search_icon.png" width="16" height="16" align="absmiddle" id="tm_search_icon" onclick="javascript:searchType1();"/>
                      </li>                
          			</ol>
                    <div id="tm_clear-left"></div>
                   
         </div>
         <div id="tm_clear"></div>
            <!--Social & Search 結束-->
            
            <!--Sub 選單 開始-->
         <div id="tm_sub_menu">
         	<ol id="tm_sub_menu_list">
            	
         			<li><img src="http://www.meettaiwan.com/images_2012/icon/sub_icon.png" width="13" height="13" align="absmiddle" id="tm_sub_icon" /> 行動版</li>
    				
            
            	<li><img src="http://www.meettaiwan.com/images_2012/icon/sub_icon.png" width="13" height="13" align="absmiddle" id="tm_sub_icon" />活動檔期</li>
            	<li><img src="http://www.meettaiwan.com/images_2012/icon/sub_icon.png" width="13" height="13" align="absmiddle" id="tm_sub_icon" />場地搜尋</li>
            	
            
    				
        			<li><img src="http://www.meettaiwan.com/images_2012/icon/sub_icon.png" width="13" height="13" align="absmiddle" id="tm_sub_icon" />會員登入</li>
    				
              <li><img src="http://www.meettaiwan.com/images_2012/icon/sub_icon.png" width="13" height="13" align="absmiddle" id="tm_sub_icon" />電子報服務</li>
         	</ol>
   </div>
         	<!--Sub 選單 結束-->
            <div id="tm_clear"></div>
            <!--Header 燈箱 開始-->
            <!-- content -->
	<div id="tm_header_img">
        <img src="http://www.meettaiwan.com/images_2012/page_header_img.png" width="1000" height="147" />
        </div>
        	
			
            
      </div>
		
</div>
</div>
<!--HEADER BOX 結束-->

</div>

<div align="center">
	






 



<div id="tm_menu_box">
<div class="tm_menulist">
<ul id="cssmenu1">

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_01_zh_TW.png" alt="台灣會展躍升計畫" name="menu_link_0" height="39" border="0" id="menu_link_0"	/>
	</li>

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_02_zh_TW.png" alt="新聞與活動" name="menu_link_1" height="39" border="0" id="menu_link_1"	/>
	</li>

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_03_zh_TW.png" alt="會展籌辦資訊" name="menu_link_2" height="39" border="0" id="menu_link_2"	/>
	</li>

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_06_zh_TW.png" alt="會展躍升獎" name="menu_link_3" height="39" border="0" id="menu_link_3"	/>
	</li>

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_08_zh_TW.png" alt="服務申請" name="menu_link_4" height="39" border="0" id="menu_link_4"	/>
	</li>

	<li><img src="http://www.meettaiwan.com/images_2012/menu/S001_09_zh_TW.png" alt="會展文獻資料" name="menu_link_5" height="39" border="0" id="menu_link_5"	/>
	</li>
                
</ul>
<br style="clear: left;" />
</div>

<div id="tm_main_center">
    <div id="tm_center_box">
<div id="tm_path">

首頁


/ 台灣會展躍升計畫


/
<c:if test="${bean.projectVO.menuId == 'S001_01_01'}">
	計畫推手 - 經濟部國際貿易局
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_02'}">
	會展產業整體推動計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_04'}">
	會展推廣與國際行銷計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_05'}">
	會展人才培育與認證計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_03'}">
	爭取國際會議在台舉辦計畫
</c:if>

</div>
      	<div id="tm_page_left">
      		






 



<div id="tm_left_menu_box">
	<div id="tm_menu_title"> 
	 	台灣會展躍升計畫 
	</div>
	 
	<div id="left_menu_content">
	  <ul id="left_menu_list">
	    
	    <li><span id="left_menu_list_span">計畫推手 - 經濟部國際貿易局</span></li>
	    
	    <li><span id="left_menu_list_span">會展產業整體推動計畫</span></li>
	    
	    <li><span id="left_menu_list_span">會展推廣與國際行銷計畫</span></li>
	    
	    <li><span id="left_menu_list_span">會展人才培育與認證計畫</span></li>
	    
	    <li><span id="left_menu_list_span">爭取國際會議在台舉辦計畫</span></li>
	    
	  </ul>
	</div>
</div>

      		






 


<div id="tm_left_menu_box">
	<div id="tm_menu_title">
		籌劃者工具 
	</div>
    <div id="left_menu_content">
       	<ul id="left_menu_list">
           <li><span id="left_menu_list_span">徵求服務企劃</span></li>
           <li><span id="left_menu_list_span">活動行事曆</span></li>
           <li><span id="left_menu_list_span">場地搜尋</span></li>
        </ul>
    </div>
</div>
      	</div>
     	 	<!--<div id="tm_page_right">
          	<div id="page_content_box">-->
            <div id="center-content"><!--************************** action-->
			<div id="rootleft">
            <div id="main">
          		






 



<div id="page_topic_box">
	<div id="page_topic_box_left">
    	<div id="page_topic_box_left1">
    	<c:if test="${bean.projectVO.menuId == 'S001_01_01'}">
	計畫推手 - 經濟部國際貿易局
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_02'}">
	會展產業整體推動計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_04'}">
	會展推廣與國際行銷計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_05'}">
	會展人才培育與認證計畫
</c:if>
<c:if test="${bean.projectVO.menuId == 'S001_01_03'}">
	爭取國際會議在台舉辦計畫
</c:if>
    	</div>
        <div id="page_topic_box_left2">&nbsp;</div>
    </div>
    <div id="page_topic_box_right"><img src="http://www.meettaiwan.com/images_2012/title_img.png" /></div>
</div>

          		






 


<div id="i18ncontent" align="justify"  style="padding:0 10px 10px 10px">



<c:if test="${bean.projectVO.menuId == 'S001_01_01'}">
<link href="http://www.meettaiwan.com/css_2012/supervisor.css" rel="stylesheet" type="text/css" />
</c:if>

<c:if test="${bean.projectVO.menuId == 'S001_01_02'}">
<link href="http://www.meettaiwan.com/css_2012/overall.css" rel="stylesheet" type="text/css" />
</c:if>

<c:if test="${bean.projectVO.menuId == 'S001_01_04'}">
<link href="http://www.meettaiwan.com/css_2012/promotion.css" rel="stylesheet" type="text/css" />
</c:if>

<c:if test="${bean.projectVO.menuId == 'S001_01_05'}">
<link href="http://www.meettaiwan.com/css_2012/certification.css" rel="stylesheet" type="text/css" />
</c:if>

<c:if test="${bean.projectVO.menuId == 'S001_01_03'}">
<link href="http://www.meettaiwan.com/css_2012/hosting.css" rel="stylesheet" type="text/css" />
</c:if>

<script type="text/javascript">
	$(function(){
		// 預設顯示第一個 Tab
		var _showTab = 0;
		$('.abgne_tab').each(function(){
			// 目前的頁籤區塊
			var $tab = $(this);

			var $defaultLi = $('ul.tabs li', $tab).eq(_showTab).addClass('active');
			$($defaultLi.find('a').attr('href')).siblings().hide();
			
			// 當 li 頁籤被點擊時...
			// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
			$('ul.tabs li', $tab).click(function() {
				// 找出 li 中的超連結 href(#id)
				var $this = $(this),
					_clickTab = $this.find('a').attr('href');
				// 把目前點擊到的 li 頁籤加上 .active
				// 並把兄弟元素中有 .active 的都移除 class
				$this.addClass('active').siblings('.active').removeClass('active');
				// 淡入相對應的內容並隱藏兄弟元素
				$(_clickTab).stop(false, true).fadeIn().siblings().hide();

				return false;
			}).find('a').focus(function(){
				this.blur();
			});
		});
	});
</script>
<div id="ts1" >計畫介紹</div>
<div id="ts_content">
<c:forEach var="pdvo" items="${bean.projectDetailVOs}">
	<c:if test="${pdvo.localizedDataId == 'PROJECT_INTRODUTION_TW'}">
		<c:out value="${pdvo.content}" escapeXml="false" />
	</c:if>
</c:forEach>
</div>

<c:if test="${taskNum > 0}">
<div id="ts1" >工作項目</div>
<div class="abgne_tab sec_tab">
		<ul class="tabs">
			<c:forEach var="pdvo" items="${bean.projectDetailVOs}" varStatus="status">
				<c:if test="${pdvo.localizedDataId == 'PROJECT_TASK_TW'}">
					<li><a href="#tab${status.count}">${pdvo.description}</a></li>
				</c:if>
			</c:forEach>
		</ul>

	<div class="tab_container">
		<c:forEach var="pdvo" items="${bean.projectDetailVOs}" varStatus="status">
			<c:if test="${pdvo.localizedDataId == 'PROJECT_TASK_TW'}">
					<div id="tab${status.count}" class="tab_content">
                		<c:out value="${pdvo.content}"  escapeXml="false"/>
		  			</div>
		  			<c:set var="counter" value="${status.count}"/>
			</c:if>
		</c:forEach>
  </div>
</div>
</c:if>

<c:if test="${resourceNum > 0}">
<div id="ts1" >服務資源</div>
<div class="abgne_tab sec_tab">
		<ul class="tabs">
		<c:forEach var="pdvo" items="${bean.projectDetailVOs}" varStatus="status">
				<c:if test="${pdvo.localizedDataId == 'PROJECT_RESOURCE_TW'}">
					<li><a href="#tab${status.count + counter}">${pdvo.description}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="tab_container">
			<c:forEach var="pdvo" items="${bean.projectDetailVOs}" varStatus="status">
				<c:if test="${pdvo.localizedDataId == 'PROJECT_RESOURCE_TW'}">
						<div id="tab${status.count + counter}" class="tab_content">
	                		<c:out value="${pdvo.content}"  escapeXml="false"/>
			  			</div>
				</c:if>
			</c:forEach>
  		</div>
	</div>
</c:if>

<c:if test="${contactNum > 0}">	
<div id="ts1" >聯絡窗口</div>
<div id="ts_content">
<c:forEach var="pdvo" items="${bean.projectDetailVOs}" varStatus="status">
				<c:if test="${pdvo.localizedDataId == 'PROJECT_CONTACT_TW'}">
					<c:out value="${pdvo.content}"  escapeXml="false"/>
				</c:if>
			</c:forEach>
</div>
</c:if>
</div>
         		 </div>
                 </div>
        	</div>
       	 <br style="clear: left;" />
	</div>
</div>







 



<!--版權宣告 開始-->

	<div id="tm_footer_inbox">
    <div id="copyright_box">
    		<div id="sub_logo">
          		<img src="http://www.meettaiwan.com/images_2012/sub_logo.png" width="160" height="72" />
          		<!-- START SCANALERT CODE -->
    			<a target="_blank" href="https://www.mcafeesecure.com/RatingVerify?ref=60.251.4.38&lang=TW"><img width="65" height="37" border="0" src="//images.scanalert.com/meter/60.251.4.38/63.gif?lang=TW" alt="McAfee SECURE 認證的網站足以預防99%以上的駭客犯罪" oncontextmenu="alert('翻印必究 - McAfee SECURE是 McAfee 公司的註冊商標'); return false;"></a>
    			<!-- END SCANALERT CODE -->
          	</div>
            <div id="company_details">
            	<div id="sub_link">
            	 
            	 	網站地圖
            	 	｜
        		
            	 	資料隱私權政策
            	 	｜
        		
            	 	網站使用條款
            	 	｜
        		
            	 	意見與建議
            	 	｜
        		
            	 	聯絡我們
            	 	
        		
                </div>
                <div style="color:#FFF;">版權所有：&copy; 2012中華民國經濟部（國際貿易局）</div>
                <div style="color:#FFF;">主辦單位：中華民國經濟部（國際貿易局）</div>
                <div style="color:#FFF;">執行單位：台灣源訊科技股份有限公司</div>
   	  	  </div>
          <div id="code_box">
       	    <img src="http://www.meettaiwan.com/images_2012/code.png" width="86" height="86" /></div>
   	  	  </div>
	</div>


<!--版權宣告 結束-->
<div style="position: relative; top: 20px;">
	<form name="projectEditForm" method="POST" action="<c:url value="project.htm?act=dirty"/>">
		<input type="hidden" name="id" value="${bean.projectVO.id}"/>
		<input type="submit" value="<spring:message code="text.dirty"/>"/>
		<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()"/>
		<c:if test="${query == 'Y'}">
			<input type="button" value="<spring:message code="text.update"/>" onClick="update('${bean.projectVO.id}')"/>
		</c:if>
	</form>
	<br/><br/>
</div>

</div>

  </div>

</div>

</body>
</html>            