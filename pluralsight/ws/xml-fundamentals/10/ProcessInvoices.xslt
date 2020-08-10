<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" version="1.0" encoding="UTF-8"/>
	<xsl:template match="auction:line-item" xmlns:auction="urn:auction-place/v2">
		<xsl:value-of select="concat('Client:', namespace-uri())"/>
		<xsl:value-of select="concat('  Extended Price:', auction:price+auction:purchaser-fee)"/>
		<xsl:value-of select="concat('  Inventory:', auction:inventory-number)"/>
<xsl:text>
</xsl:text>		
	</xsl:template>
	<xsl:template match="retail:line-item" xmlns:retail="urn:retail-place/v1">
		<xsl:value-of select="concat('Client:', namespace-uri())"/>
		<xsl:value-of select="concat('  Extended Price:', retail:price*retail:quantity)"/>
		<xsl:value-of select="concat('  Description:', retail:description)"/>
<xsl:text>
</xsl:text>
	</xsl:template>
<xsl:template match="text()"/>
</xsl:stylesheet>
