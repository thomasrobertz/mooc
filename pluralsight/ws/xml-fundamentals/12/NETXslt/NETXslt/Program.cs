using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Xsl;

namespace NETXslt
{
  class Program
  {
    static void Main(string[] args)
    {
      var input = args[0];
      var output = args[1];
      var xslt = args[2];
      var settings = new XmlReaderSettings();
      settings.DtdProcessing = DtdProcessing.Parse;
      var xInputReader = XmlReader.Create(input, settings);
      var xTransformReader = XmlReader.Create(xslt, settings);
      var xOutputWriter = XmlWriter.Create(output);
      var transform = new XslCompiledTransform();
      transform.Load(xTransformReader);
      transform.Transform(xInputReader, xOutputWriter);
 
    }
  }
}
