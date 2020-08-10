<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="node()">
		<xsl:copy/>
	</xsl:template>
	<xsl:template match="*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="*[namespace-uri()!='']">
		<xsl:copy>
			<xsl:attribute name="ns">
				<xsl:value-of select="namespace-uri()"/>
			</xsl:attribute>
			<xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="@*[namespace-uri()!='']">
		<xsl:attribute name="{local-name(.)}" namespace="{namespace-uri()}">
			<xsl:value-of select="concat('[', namespace-uri(), ']', .)"/>
		</xsl:attribute>
	</xsl:template>
	<xsl:template match="@*">
		<xsl:copy/>
	</xsl:template>
</xsl:stylesheet>
