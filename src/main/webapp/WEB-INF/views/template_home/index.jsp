<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
    * {
        font-family: 'Inter', sans-serif;
    }
</style>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Montserrat:wght@100;200;300;400;500;600&family=Roboto:wght@100;300;400;500&display=swap"

      rel="stylesheet">
<link rel="stylesheet" href="../../../plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../../../plugins/ps-icon/style.css">
<link rel="stylesheet" href="../../../plugins/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../../../plugins/owl-carousel/assets/owl.carousel.css">
<link rel="stylesheet" href="../../../plugins/jquery-bar-rating/dist/themes/fontawesome-stars.css">
<link rel="stylesheet" href="../../../plugins/slick/slick/slick.css">
<link rel="stylesheet" href="../../../plugins/bootstrap-select/dist/css/bootstrap-select.min.css">
<link rel="stylesheet" href="../../../plugins/Magnific-Popup/dist/magnific-popup.css">
<link rel="stylesheet" href="../../../plugins/jquery-ui/jquery-ui.min.css">
<link rel="stylesheet" href="../../../plugins/revolution/css/settings.css">
<link rel="stylesheet" href="../../../plugins/revolution/css/layers.css">
<link rel="stylesheet" href="../../../plugins/revolution/css/navigation.css">
<link rel="stylesheet" href="../../../css_template/style.css">
<link rel="stylesheet" href="../../../css_update_template/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%----%>
<link rel="stylesheet" type="text/css" href="../../../css_update_template/main_styles.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/single_styles.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/responsive.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/animate.css">
<%--<link rel="stylesheet" type="text/css" href="../../../css_update_template/categories_styles.css">--%>
<%--<link rel="stylesheet" type="text/css" href="../../../css_update_template/categories_responsive.css">--%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%@include file="header.jsp" %>
<div>
    <jsp:include page="${view}"/>
</div>
<%@include file="footer.jsp" %>

<!-- JS Library-->
<script type="text/javascript" src="../../../plugins/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="../../../plugins/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../plugins/jquery-bar-rating/dist/jquery.barrating.min.js"></script>
<script type="text/javascript" src="../../../plugins/owl-carousel/owl.carousel.min.js"></script>
<script type="text/javascript" src="../../../plugins/gmap3.min.js"></script>
<script type="text/javascript" src="../../../plugins/imagesloaded.pkgd.js"></script>
<script type="text/javascript" src="../../../plugins/isotope.pkgd.min.js"></script>
<script type="text/javascript" src="../../../plugins/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="../../../plugins/jquery.matchHeight-min.js"></script>
<script type="text/javascript" src="../../../plugins/slick/slick/slick.min.js"></script>
<script type="text/javascript" src="../../../plugins/elevatezoom/jquery.elevatezoom.js"></script>
<script type="text/javascript" src="../../../plugins/Magnific-Popup/dist/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="../../../plugins/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="../../../plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script type="text/javascript"
        src="../../../plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script type="text/javascript"
        src="../../../plugins/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
<script type="text/javascript"
        src="../../../plugins/revolution/js/extensions/revolution.extension.actions.min.js"></script>
<!-- Custom scripts-->
<script type="text/javascript" src="../../../js_template/main.js"></script>
<script type="text/javascript" src="../../../js_template/custom.js"></script>
<script>
    function setHeader()
    {
        if(window.innerWidth < 992)
        {
            if($(window).scrollTop() > 100)
            {
                header.css({'top':"0"});
            }
            else
            {
                header.css({'top':"0"});
            }
        }
        else
        {
            if($(window).scrollTop() > 100)
            {
                header.css({'top':"-50px"});
            }
            else
            {
                header.css({'top':"0"});
            }
        }
        if(window.innerWidth > 991 && menuActive)
        {
            closeMenu();
        }
    }
    setHeader();
</script>