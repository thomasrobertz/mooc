using System;
using System.Xml;
namespace InvoiceReport
{
  class Program
  {
    static void Main(string[] args)
    {
      var xdoc = new XmlDocument();
      xdoc.Load(args[0]);
      var invoices = xdoc.DocumentElement;
      if (invoices.NamespaceURI == "urn:invoice-package" && invoices.LocalName=="invoices")
      {
        foreach (XmlElement invoice in invoices.SelectNodes("*"))
        {
          foreach (XmlElement lineItem in invoice.SelectNodes("line-item"))
          {
            var nav = lineItem.CreateNavigator();
            switch (invoice.NamespaceURI)
            {
              case "urn:auction-place/v2":
                Console.WriteLine("Client: {0}  Extended Price: {1}  Inventory: {2}",
                    invoice.NamespaceURI,
                    nav.Evaluate("price + purchaser-fee"),
                    nav.Evaluate("string(inventory-number)"));
                break;
              case "urn:retail-place/v1":
                Console.WriteLine("Client: {0}  Extended Price: {1}  Description: {2}",
                    invoice.NamespaceURI,
                    nav.Evaluate("price * quantity"),
                    nav.Evaluate("string(description)"));
                break;
            }
          }
        }
      }
    }
  }
}
