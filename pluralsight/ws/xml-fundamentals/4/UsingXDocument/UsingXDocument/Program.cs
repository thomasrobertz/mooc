using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace UsingXDocument
{
    class Program
    {
        static void Main(string[] args)
        {
            var xdoc = XDocument.Load("items.xml");
            var query = from a in xdoc.Element("items").Attributes("cpr") select a.Value;
            Console.WriteLine(query.ToArray()[0]);
            var doct = xdoc.DocumentType';
            Console.WriteLine(doct.ToString());
        }
    }
}
