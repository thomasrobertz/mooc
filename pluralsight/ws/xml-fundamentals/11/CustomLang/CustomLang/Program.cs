using System;
using System.Xml;

namespace CustomLang
{
  class Program
  {
    static void Main(string[] args)
    {
      var xdoc = new XmlDocument();
      xdoc.Load(args[0]);
      foreach (XmlElement item in xdoc.SelectNodes("//item"))
      {
        var format = "price {0}  length {1}  width {2}";
        var nav = item.CreateNavigator();
        switch (item.GetAttribute("lang"))
        {
          case "en-us":
            Console.WriteLine(format, 
              nav.Evaluate("concat(price, ' US dollars')"),
              nav.Evaluate("concat(length, ' feet')"),
              nav.Evaluate("concat(width, ' feet')"));
            break;
          case "en-gb":
            Console.WriteLine(format,
              nav.Evaluate("concat(price, ' pounds')"),
              nav.Evaluate("concat(length, ' meters')"),
              nav.Evaluate("concat(width, ' meters')"));
            break;
        }
      }
    }
  }
}
