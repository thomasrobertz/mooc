using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
namespace UsingXmlDocument
{
    class Program
    {
        static void Main(string[] args)
        {
            var xdoc = new XmlDocument();
            xdoc.Load("items.xml");
            var doct = (XmlDocumentType)xdoc.DocumentType;
            var house = (XmlEntity)doct.Entities.GetNamedItem("house");
            var houseNotation = house.NotationName;
            var notation = (XmlNotation)doct.Notations.GetNamedItem(houseNotation);
            Console.WriteLine("{0}->{1}", house.SystemId, notation.SystemId);
        }
    }
}
