using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace XmlLang
{
  class Program
  {
    static KeyValuePair<string, string>[]
      monetary_units = new KeyValuePair<string, string>[]
      {
        new KeyValuePair<string,string>("en-us", "US dollars"),
        new KeyValuePair<string,string>("en-gb", "pounds"),
        new KeyValuePair<string,string>("en", "pounds"),
      };
    static KeyValuePair<string, string>[]
      linear_units = new KeyValuePair<string, string>[]
      {
        new KeyValuePair<string,string>("en-us", "feet"),
        new KeyValuePair<string,string>("en-gb", "meters"),
        new KeyValuePair<string,string>("en", "meters"),
      };
    static string Lang(string key)
    {
      var langFunctionCall=string.Format("lang('{0}')", key);
      return langFunctionCall;
    }
    static string UnitDescription(
      XmlNode ele, KeyValuePair<string, string>[] unit_descriptions)
    {
      var nav = ele.CreateNavigator();
      var unit_description = unit_descriptions.FirstOrDefault(
        b => (bool)nav.Evaluate(Lang(b.Key))).Value;
      return unit_description != null ? unit_description : "??";
    }
    static string MonetaryDescription(XmlNode ele)
    {
      return UnitDescription(ele, monetary_units);
    }
    static string LinearDescription(XmlNode ele)
    {
      return UnitDescription(ele, linear_units);
    }
    static void Main(string[] args)
    {
      var xdoc = new XmlDocument();
      xdoc.Load(args[0]);
      var format = "price {0} {1} length {2} {3} width {4} {5}";
      foreach (XmlElement item in xdoc.SelectNodes("//item"))
      {
        var price = item.SelectSingleNode("price");
        var length = item.SelectSingleNode("length");
        var width = item.SelectSingleNode("width");
        Console.WriteLine(format,
          price.InnerText, MonetaryDescription(price),
          length.InnerText, LinearDescription(length),
          width.InnerText, LinearDescription(width));
      }
    }
  }
}
