using System;
using System.Xml;

namespace OptionalAttribute
{
    class Program
    {
        static void Main(string[] args)
        {
            var settings = new XmlReaderSettings();
            settings.DtdProcessing = DtdProcessing.Parse;
            var xreader = XmlReader.Create(args[0], settings);
            var xdoc = new XmlDocument();
            xdoc.Load(xreader);
            var sizeAttribute = xdoc.SelectSingleNode("/item/@size");
            var stockroomAttribute = xdoc.SelectSingleNode(
                "/item/@stockroom");
            Console.WriteLine("size {0}",sizeAttribute!=null ?
                sizeAttribute.Value : "not found");
            Console.WriteLine("stockroom {0}",
                stockroomAttribute!=
                null ? stockroomAttribute.Value : "not found");
        }
    }
}
