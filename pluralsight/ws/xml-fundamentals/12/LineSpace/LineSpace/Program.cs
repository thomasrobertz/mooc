using System;
using System.Xml;
using System.Text.RegularExpressions;
namespace LineSpace
{
  class Program
  {
    static void Main(string[] args)
    {
      var input = args[0];
      var xReader = XmlReader.Create(input);
      while (xReader.Read())
      {
        Console.WriteLine(
          "Type:{1} {0} Value:'{2}'",
          xReader.LocalName,
          xReader.NodeType,  Regex.Replace(xReader.Value, "[ \t]", "\\s"));
      }
    }
  }
}
