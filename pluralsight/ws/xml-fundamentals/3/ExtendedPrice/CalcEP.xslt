<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:msxsl="urn:schemas-microsoft-com:xslt" exclude-result-prefixes="msxsl" 
>
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <xsl:template match="/">
	    <xsl:apply-templates select="//item"/>
	</xsl:template>
  <xsl:template match="item">
    Part Number: <xsl:value-of select="@pn"/> Extended Price: <xsl:value-of select="@qty * @price"/>
  </xsl:template>
</xsl:stylesheet>

