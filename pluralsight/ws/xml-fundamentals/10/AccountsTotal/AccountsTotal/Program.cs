using System;
using System.Xml;

namespace AccountsTotal
{
    class Program
    {
        static void Main(string[] args)
        {
            var xdoc = new XmlDocument();
            xdoc.Load(args[0]);
            var nm = new XmlNamespaceManager(xdoc.NameTable);
            nm.AddNamespace("ac", "urn:accounting");
            Console.WriteLine(
                (double)xdoc.CreateNavigator().Evaluate(
                "sum(//ac:balance)", nm));
        }
    }
}
