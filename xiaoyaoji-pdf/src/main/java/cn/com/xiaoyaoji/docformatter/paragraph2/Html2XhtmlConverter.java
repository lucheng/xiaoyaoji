package cn.com.xiaoyaoji.docformatter.paragraph2;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.*;

import java.util.List;
import java.util.Objects;

/**
 * 说明
 *
 * @author luofe
 * @date 2018/10/10
 */
public class Html2XhtmlConverter {

  public static String convert(String html) {

    if (html == null) {
      return null;
    }
    return convert(Jsoup.parse(html));
  }

  public static String convert(Node node) {

    return convert(node, "en", null, false, false);
  }

  public static String convert(Node node, String lang, String encoding, boolean needNl, boolean insidePre) {

    StringBuilder text = new StringBuilder();
    List<Node> children = node.childNodes();
    int childLength = children.size();
    String tagName;
    boolean doNl = needNl;
    boolean pageMode = true;

    for (int i = 0; i < childLength; i++) {
      Node child = children.get(i);

      if (child instanceof Element) {
        tagName = ((Element) child).tagName();
        if (StringUtil.isBlank(tagName)) {
          continue;
        }

        if ("meta".equalsIgnoreCase(tagName)) {
          if ("generator".equalsIgnoreCase(child.attr("name"))) {
            continue;
          }
        }

        if (!needNl && "body".equalsIgnoreCase(tagName)) {
          //html fragment mode
          pageMode = false;
        }

        if ("!".equals(tagName)) {
          continue;
        } else {
          if ("html".equals(tagName)) {
            if (encoding == null) {
              encoding = "UTF-8";
            }
            text = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"").append(encoding).append(
                "\"?>\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
          }

          text.append('<').append(tagName);
          List<Attribute> attrs = child.attributes().asList();
          int attrSize = attrs.size();
          for (int j = 0; j < attrSize; j++) {
            Attribute attr = attrs.get(j);
            String attrName = attr.getKey();
            text.append(" ").append(attrName).append("=\"").append(fixAttribute(attr.getValue())).append('"');
          }
          if (!child.childNodes().isEmpty()) {
            text.append('>');
            text.append(convert(child, lang, encoding, true, insidePre || "pre".equals(tagName)));
            text.append("</").append(tagName).append('>');
          } else {

            if (Objects.equals(tagName, "style") || Objects.equals(tagName, "title") || Objects
                .equals(tagName, "script")) {

              text.append('>');
              String innerText;
              if (Objects.equals(tagName, "script")) {
                innerText = ((Element) child).text();
              } else {
                innerText = ((Element) child).html();
              }

              if (Objects.equals(tagName, "style")) {
                innerText = innerText.replaceAll("\n+", "\n");
              }

              text.append(innerText).append("</").append(tagName).append('>');

            } else {
              text.append(" />");
            }
          }
        }

      } else if (child instanceof TextNode) {
        String tmp = ((TextNode) child).text();
        if (!insidePre) {
          //do not change text inside <pre> tag
          if (!"\n".equalsIgnoreCase(tmp)) {
            text.append(fixText(tmp));
          }
        } else {
          text.append(tmp);
        }
      } else if (child instanceof Comment) {
        text.append(fixComment(child.toString()));
      }
    }

    String result = text.toString();
    if (!needNl && !pageMode) {
      //delete head and body tags from html fragment
      result = result.replaceAll("</head>\n*", "");
      result = result.replaceAll("<head />\n*", "");
      result = result.replaceAll("</body>\n*", "");
    }

    return result;
  }

  private static String fixComment(String text) {

    text = text.replaceAll("--", "__");
    if (text.endsWith("-")) {
      text += " ";
    }
    return "<!--" + text + "-->";
  }

  private static String fixText(String text) {
    //convert <,> and & to the corresponding entities
    text = text.replaceAll("\n+", "\n");
    text = text.replaceAll("&", "&amp;");
    text = text.replaceAll("<", "&lt;");
    text = text.replaceAll(">", "&gt;");
    text = text.replaceAll("\\u00A0", "&nbsp;");
    return text;
  }

  private static String fixAttribute(String text) {
    //convert <,>, & and " to the corresponding entities
    text = text.replaceAll("\n+", "\n");
    text = text.replaceAll("&", "&amp;");
    text = text.replaceAll("\"", "&quot;");
    text = text.replaceAll("<", "&lt;");
    text = text.replaceAll(">", "&gt;");
    return text;
  }
}
