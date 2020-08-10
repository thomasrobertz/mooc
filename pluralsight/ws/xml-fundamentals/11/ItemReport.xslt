<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:data="urn:data">
	<xsl:output method="text" version="1.0" encoding="UTF-8" indent="yes"/>
	<monetary-units xmlns="urn:data">
		<unit xml:lang="en-us" description="US dollars"/>
		<unit xml:lang="en-gb" description="pounds"/>
		<unit xml:lang="en" description="pounds"/>
	</monetary-units>
	<linear-units xmlns="urn:data">
		<unit xml:lang="en-us" description="feet"/>
		<unit xml:lang="en-gb" description="meters"/>
		<unit xml:lang="en" description="meters"/>
	</linear-units>
	<xsl:variable name="monetary-units" select="document('')/*/data:monetary-units/*"/>
	<xsl:variable name="linear-units" select="document('')/*/data:linear-units/*"/>
	<xsl:template match="item">
		<xsl:apply-templates select="price | length | width "/>
		<xsl:text>
</xsl:text>
	</xsl:template>
	<xsl:template match="price">
		<xsl:call-template name="unit-description">
			<xsl:with-param name="descriptions" select="$monetary-units"/>
		</xsl:call-template>
	</xsl:template>
	<xsl:template match="length | width">
		<xsl:call-template name="unit-description">
			<xsl:with-param name="descriptions" select="$linear-units"/>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="unit-description">
		<xsl:param name="descriptions"/>
		<xsl:value-of select="concat(local-name(), ': ', ., '  ')"/>
		<xsl:apply-templates select="$descriptions[1]">
			<xsl:with-param name="ele" select="."/>
		</xsl:apply-templates>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="data:unit">
		<xsl:param name="ele"/>
		<xsl:choose>
			<xsl:when test="$ele[lang(current()/@xml:lang)]">
				<xsl:value-of select="@description"/>
			</xsl:when>
			<xsl:when test="not((following-sibling::*)[1])">??</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select="(following-sibling::*)[1]">
					<xsl:with-param name="ele" select="$ele"/>
				</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
