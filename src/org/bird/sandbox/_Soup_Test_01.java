package org.bird.sandbox;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.function.Consumer;

public class _Soup_Test_01 {

    public static void main(String[] args) {

        try {
            //JsoupTree tree = new JsoupTree("http://www.elbakin.net/fantasy/roman/cycle/le-livre-des-martyrs-77");
            JsoupTree tree = new JsoupTree("https://www.amazon.fr/Livre-Martyrs-T1-Jardins-Lune/dp/B079VF7Q12/ref=pd_sim_14_3/259-5184781-6798858?_encoding=UTF8&pd_rd_i=B079VF7Q12&pd_rd_r=26744903-85e9-11e9-83ee-f7b9de9ca136&pd_rd_w=XbGZd&pd_rd_wg=cZ0xv&pf_rd_p=7e133c34-2a2a-4f4f-8372-6a26c70ca073&pf_rd_r=QDGGN50Y0DMGS5ZKJDAY&psc=1&refRID=QDGGN50Y0DMGS5ZKJDAY");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class JsoupTree{

        public JsoupTree(String url) throws IOException {
            Connection connect = Jsoup.connect(url).parser(Parser.htmlParser());
            connect.userAgent("Mozilla");
            connect.timeout(5000);
            connect.cookie("cookiename", "val234");
            connect.cookie("cookiename", "val234");
            connect.referrer("http://google.com");
            connect.header("headersecurity", "xyz123");
            Connection.Request req = connect.request();
            Parser parser = new Parser(req.parser().getTreeBuilder());

            Document document = connect.get();
            traverseRecursively(document.getAllElements().first(),1);
        }

        public void traverseRecursively(Node docNode, int index){
            String str = "-";
            for (Node nextChildDocNode : docNode.childNodes()){
                System.out.println(str.repeat(index)+nextChildDocNode.nodeName());
                if (nextChildDocNode.nodeName().equalsIgnoreCase("#text")){
                    System.out.println(nextChildDocNode.outerHtml());
                }
                traverseRecursively(nextChildDocNode,index++);
            }
        }
    }

}
