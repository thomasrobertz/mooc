using System;
using System.Xml;

namespace ProcessParasFromBody
{
    class Program
    {
        static void Main(string[] args)
        {
            var chapterPath =
"../../TwiceRememberedBadChapterNoDTD.xml";
            var xdoc = new XmlDocument();
            xdoc.Load(chapterPath);
            var body = xdoc.SelectSingleNode("/chapter/body");
            foreach (XmlElement para in body.SelectNodes("para"))
            {
                Console.WriteLine(para.InnerText);
                Console.WriteLine();
            }
        }
    }
}
