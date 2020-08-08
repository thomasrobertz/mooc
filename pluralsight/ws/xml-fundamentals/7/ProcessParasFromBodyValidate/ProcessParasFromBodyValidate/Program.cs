using System;
using System.Xml;
using System.Xml.Schema;

namespace ProcessParasFromBodyValidate
{
    class Program
    {
        static void Main(string[] args)
        {
          var chapterPath = @"M:\Pluralsight Video Courses\FundamentalOfXML\05-Type-Unparsed-Entities\examples\xml-fund-ele\TwiceRememberedBadChapter.xml";
            var xdoc = new XmlDocument();
            var settings = new XmlReaderSettings();
            settings.ValidationType = ValidationType.DTD;
            settings.DtdProcessing = DtdProcessing.Parse;
            var xreader = XmlReader.Create(chapterPath, settings);
            try
            {
                xdoc.Load(xreader);
            }
            catch (XmlSchemaValidationException ex)
            {
                Console.WriteLine(ex.Message);
                return;
            }
            var body = xdoc.SelectSingleNode("/chapter/body");
            foreach (XmlElement para in body.SelectNodes("para"))
            {
                Console.WriteLine(para.InnerText);
                Console.WriteLine();
            }
        }
    }
}
