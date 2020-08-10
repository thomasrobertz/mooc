using System;
using System.Xml;

namespace Namespaces
{
    class Program
    {
        static void Main(string[] args)
        {
            var xdoc = new XmlDocument();
            xdoc.Load(args[0]);
            foreach (XmlElement ele in xdoc.SelectNodes("//*"))
            {
                Console.WriteLine("Ele Name '{0}' Namespace '{1}'",
                    ele.LocalName, ele.NamespaceURI);
                foreach (XmlAttribute atr in ele.Attributes)
                {
                    Console.WriteLine("   Attr Name '{0}' Namespace '{1}'",
                        atr.LocalName, atr.NamespaceURI);

                }
            }
        }
    }
}
