	for $part in (//gear|//bolt) 
	let $name:= local-name($part)
	return concat("&#10;Found ", $name )

