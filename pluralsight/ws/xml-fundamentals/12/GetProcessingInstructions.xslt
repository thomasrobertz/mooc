<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="text()"/>
	<xsl:template match="processing-instruction()">
	PI: <xsl:value-of select="concat('(', local-name(), '):', .)"/>
	</xsl:template>
</xsl:stylesheet>
