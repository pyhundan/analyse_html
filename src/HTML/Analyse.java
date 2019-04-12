package HTML;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.print.Doc;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Analyse {
    public String url;//网页的URL
    public Document doc;

    public LinkedList imgURLs=new LinkedList();//图片URL




    public Analyse(String url2,Document doc2)
    {
        this.url=url2;
        this.doc=doc2;

    }
    //网页标题
    public String getTitle(Document doc) throws IOException
    {
        String title = "\n标题:\n"+doc.title();
        return title;
    }

    //获取class:warp 标签里的内容
    public String get_class_warp(Document doc) throws IOException
    {
        String text="\nclass:warp 标签里的内容:\n";
        Elements links = doc.getElementsByClass("wrap");
        for (Element link : links) {
            //System.out.println(link.text());
            text+=link.text()+'\n';

        }
        return  text;
    }
    //获取所有javascript内容
    public String get_js(Document doc) throws IOException
    {
        String text="\njavascript：\n";
        Elements els = doc.getElementsByTag("script");

        //System.out.println("\n\n\n" + els + "\n");
        //System.out.println("\n\n\n" + els.text() + "\n");
        text+=els.toString();
        return text;
    }

    //获取所有td标签
    public String get_td(Document doc) throws IOException
    {
        String text="\n标签td:\n";

        Elements el = doc.getElementsByTag("td");
        text+=el.toString();
        return text;

    }

    //获取整个网页的html代码
    public String get_html(Document doc) throws IOException
    {
        String text="\n网页html代码：\n";
        Elements els = doc.getElementsByTag("html");
        text+=els.toString();
        return text;
    }

    //获取标签Meta
    public String get_meta(Document doc) throws IOException
    {
        String text="\n标签meta:\n";
        Elements els = doc.getElementsByTag("meta");
        text+=els.toString();
        return text;
    }
    //获取标签Meta里的内容
    public String get_meta_msg(Document doc) throws IOException
    {
        String text="\n标签meta内容:\n";
        Elements lit1 = doc.select("meta");
        // 使用循环遍历每个标签数据
        for (Iterator<Element> it = lit1.iterator(); it.hasNext(); ) {
            Element esta = (Element) it.next();
            // 输出其text值和其属性值
            //System.out.println(esta.text() + " " + esta.attr("content"));
            text+=esta.text().toString()+" "+esta.attr("content").toString()+'\n';
        }

        return text;
    }

    //获取网页所有url连接
    public String get_allurl(Document doc) throws IOException
    {
        String text="\nurl : \n";
        Elements href = doc.select("a[href]");
        for (Element hre : href)
        {
            String linkUrl = hre.attr("abs:href")+" "+hre.text();//获取网页的绝对地址
            text+=linkUrl;
            //System.out.println(linkUrl);

        }
        return text;
    }

    //获取网页图片url连接
    public String get_imgurl(Document doc) throws IOException{
        //int count =0;
        String text="\nimg url:\n";
        Elements imag=doc.select("img[src]");
        for (Element i: imag)
        {

            String imagurl=i.attr("abs:src");//图片的绝对路径
            text+=imagurl+'\n';
            //System.out.println(imagurl);
        }
        return text;
    }
    //获取table
    public String get_table(Document doc)throws IOException
    {
        int count=0;
        String text="\ntable 标签:\n";
        Elements litg1 = doc.getElementsByTag("table");
        for (Element ele:litg1)
        {
            count++;
        }
        System.out.println(count);
        text+=litg1.toString();
        return text;
    }

    //获取表格内容
    public String get_table_td(Document doc) throws IOException {
        int count=0;
        String text = "\ntable td: \n";
        Elements lit2 = doc.select("table");
        // 使用循环遍历每个标签数据
        for (Iterator<Element> it = lit2.iterator(); it.hasNext(); ) {
            Element ests = (Element) it.next();
            // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）
            System.out.println(ests.text() + " " + ests.attr("td"));
            text += ests.text().toString() + " " + ests.attr("td").toString()+'\n';

        }
        return text;
    }
    //获取tr
    public String get_table_tr(Document doc) throws  IOException
    {
        String text="\ntable tr:\n";
        //System.out.println("=====================tr=============================================");
        Elements lit3 = doc.select("table");
        // 使用循环遍历每个标签数据
        for (Iterator<Element> it = lit3.iterator(); it.hasNext(); ) {
            Element ests = (Element) it.next();
            // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）
            System.out.println(ests.text() + " " + ests.attr("tr"));
            text+=ests.text().toString()+" "+ests.attr("tr").toString()+'\n';
        }
        return text;
    }
    //table内容
    public String get_table_msg(Document doc) throws IOException
    {
        int count=0;
        String text1="\ntable 内容：\n";

        Elements trsw1 = doc.select("table").select("tr");
        for (int i = 0; i < trsw1.size(); i++) {
            Elements tds = trsw1.get(i).select("td");
            for (int j = 0; j < tds.size(); j++) {
                String text = tds.get(j).text();
                //System.out.print(text);
                text1+=text+'\n';
                count++;
            }
            //System.out.println();
        }

        //System.out.println(count);
        return text1;

    }
    public String get_a(Document doc) throws IOException
    {
        String text="\n a 标签: \n";

        Elements ListDivs = doc.getElementsByAttributeValue("id", "clist");
        for (Element element : ListDivs) {
            Elements ln = element.getElementsByTag("a");
            text+=ln.text().toString()+'\n';
        }
        return text;
    }
    //h标题
    public String getHs(Document doc) throws IOException{
        //List<HData> hDataList=new ArrayList<HData>();
        String text="\nh标题:\n";
        Elements attr1 = doc.getElementsByTag("h1");
        for (Element element : attr1) {
            //HData hData=new HData("h1",element.text());//实体对象构造方法，代码省略
            //hDataList.add(hData);
            text+=element.text().toString()+'\n';
        }
        Elements attr2 = doc.getElementsByTag("h2");
        for (Element element : attr2) {
            //HData hData=new HData("h2",element.text());
            //hDataList.add(hData);
            text+=element.text().toString()+'\n';
        }
        return text;
    }
    public String get_ul_li(Document doc) throws IOException {

        String text="\nul里的li标签：\n";
        //System.out.println("=====================ul= li============================================");
        // 使用jsoup将html里面的a标签里面的数据全部读取出来（假如想读取其他标签，直接将a改为其他标签名称即可，例如"img"）
        Elements lite = doc.select("ul");
        // 使用循环遍历每个标签数据
        for (Iterator<Element> it1 = lite.iterator(); it1.hasNext(); ) {
            Element estsw = (Element) it1.next();
            // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）
            //System.out.println(estsw.text() + " " + estsw.attr("li"));
            text+=estsw.text().toString()+" "+estsw.attr("li").toString()+'\n';
        }
        return text;
    }

    public String get_p(Document doc) throws IOException {
        String text="\n p标签 \n";
        Elements links = doc.getElementsByTag("p");
        for (Element ele :links)
        {
            text+=links.text().toString()+'\n';
        }
        return text;
    }


}
