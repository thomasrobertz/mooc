<items>
{
	for $item in /items/item
	let $extended := $item/@price * $item/@quantity
	return <item name="{$item/@name}" 
		price="{$item/@price}" 
		quantity="{$item/@quantity}" 
		extended="{$extended}"/>
}
</items>(: Stylus Studio meta-information - (c) 2004-2007. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" useresolver="yes" url="itemsWithPrice.xml" outputurl="" processortype="saxon" tcpport="7209033" profilemode="0" profiledepth="" profilelength="" urlprofilexml=""
		          commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="6619250" user="" password="" validateoutput="no"
		          validator="internal" customvalidator="">
			<advancedProperties name="bSchemaAware" value="true"/>
			<advancedProperties name="bXml11" value="false"/>
			<advancedProperties name="iValidation" value="0"/>
			<advancedProperties name="bExtensions" value="true"/>
			<advancedProperties name="iWhitespace" value="0"/>
			<advancedProperties name="bTinyTree" value="true"/>
			<advancedProperties name="bUseDTD" value="false"/>
			<advancedProperties name="bWarnings" value="true"/>
		</scenario>
	</scenarios>
	<MapperMetaTag>
		<MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
		<MapperBlockPosition></MapperBlockPosition>
		<TemplateContext></TemplateContext>
		<MapperFilter side="source"></MapperFilter>
	</MapperMetaTag>
</metaInformation>
:)