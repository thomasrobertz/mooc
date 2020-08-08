using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Schema;

namespace Validate
{
    class Program
    {
        static void Main(string[] args)
        {
            var settings = new XmlReaderSettings();
            settings.DtdProcessing = DtdProcessing.Parse;
            settings.ValidationType = ValidationType.DTD;
            var xreader = XmlReader.Create(args[0], settings);
            var xdoc = new XmlDocument();
            try
            {
                xdoc.Load(xreader);
            }
            catch (XmlSchemaException  ex)
            {
                Console.WriteLine(ex.Message);
                return;
            }
            Console.WriteLine("Valid");
        }
    }
}
