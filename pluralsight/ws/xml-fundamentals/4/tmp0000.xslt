<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output omit-xml-declaration="yes" indent="yes"/>

 <xsl:template match="/">
  <xsl:call-template name="printToN"/>
 </xsl:template>

 <xsl:template name="printToN">
  <xsl:param name="pUpTo" select="1000000"/>
  <xsl:param name="pDoneWith" select="0"/>

  <xsl:if test="$pUpTo > $pDoneWith">
   <xsl:value-of select="$pDoneWith+1"/>
   <xsl:text>&#xA;</xsl:text>

   <xsl:call-template name="printToN">
    <xsl:with-param name="pUpTo" select="$pUpTo"/>
    <xsl:with-param name="pDoneWith" select="$pDoneWith+1"/>
   </xsl:call-template>
  </xsl:if>
 </xsl:template>
</xsl:stylesheet>
