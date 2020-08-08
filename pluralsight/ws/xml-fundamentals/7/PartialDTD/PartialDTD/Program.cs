using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace PartialDTD
{
    class Program
    {
        static void Main(string[] args)
        {
            var settings = new XmlReaderSettings();
            settings.DtdProcessing = DtdProcessing.Parse;
            settings.ValidationType = ValidationType.None;
            var xreader = XmlReader.Create(args[0], settings);
            var xdoc = new XmlDocument();
            xdoc.Load(xreader);
            var attribute =
                (XmlAttribute)xdoc.SelectSingleNode(
                "/items/item/@color");
            Console.WriteLine("\"{0}\"", attribute.Value);
        }
    }
}
