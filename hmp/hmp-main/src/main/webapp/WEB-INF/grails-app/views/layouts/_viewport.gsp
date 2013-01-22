<%-- The actual meat of the layout is in a shared template that will create and configure an AppBar --%>
<g:render template="/layouts/extjs"/>

<%-- In addition to the basic EXTJS setup, define a viewport that uses the body content by default --%>
<script type="text/javascript" charset="utf-8">
    Ext.require('EXT.DOMAIN.hmp.Viewport');
    Ext.onReady(function () {
        // construct a simple viewport with some convenience functions to make it easy to configure the data.
        Ext.create('EXT.DOMAIN.hmp.Viewport');
    });
</script>