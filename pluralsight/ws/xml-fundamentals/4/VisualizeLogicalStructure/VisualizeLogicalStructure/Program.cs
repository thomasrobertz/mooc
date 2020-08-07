using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using System.Xml;

namespace VisualizeLogicalStructure
{
    class Program
    {
        static void Main(string[] args)
        {
            var path = @"M:\Pluralsight Video Courses\FundamentalOfXML\02-XMLRecommendation\Examples\book.xml";
            var xdoc = new XmlDocument();
            var xsettings = new XmlReaderSettings();
            xsettings.ProhibitDtd=false;
            xsettings.DtdProcessing = DtdProcessing.Parse;
            var xreader = XmlReader.Create(@"M:\Pluralsight Video Courses\FundamentalOfXML\02-XMLRecommendation\Examples\book.xml", xsettings);
            xdoc.Load(path);         
            var xwriter = new XmlTextWriter(Console.Out);
            xwriter.Formatting = Formatting.Indented;
            xdoc.DocumentElement.WriteTo(xwriter);
        }
    }
}
