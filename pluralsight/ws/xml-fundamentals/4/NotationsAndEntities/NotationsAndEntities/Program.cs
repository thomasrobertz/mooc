using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NotationsAndEntities
{
    class Program
    {
        static void Main(string[] args)
        {
            var xdoc = new XmlDocument();
            var settings = new XmlReaderSettings();
            settings.DtdProcessing = DtdProcessing.Parse;
            settings.ValidationType = ValidationType.DTD;
            var xrdr = XmlReader.Create(@"itemsdtd.xml", settings);
            xdoc.Load(xrdr);
            var name = xdoc.SelectSingleNode(@"/items/item[1]/name");
            var image = name.Attributes["image"];
            var entity = (XmlEntity)xdoc.DocumentType.Entities.GetNamedItem(image.Value);
            var notation = (XmlNotation)xdoc.DocumentType.Notations.GetNamedItem(entity.NotationName);
 
        }
    }
}
