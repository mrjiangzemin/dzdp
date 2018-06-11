package webcollector2;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Document;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class NewsCrawler extends BreadthCrawler {

    /**
     * @param crawlPath crawlPath is the path of the directory which maintains
     * information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     * links which match regex rules from pag
     */
    public NewsCrawler(String crawlPath, boolean autoParse) {
    super(crawlPath, autoParse);
    /*start page*/
    
    /*do not fetch jpg|png|gif*/
    this.addRegex("-.*\\.(jpg|png|gif).*");
    /*do not fetch url contains #*/
    this.addRegex("-.*#.*");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
    String url = page.url();
    /*if page is news page*/
    if (page.matchUrl("https://www.dianping.com/shop/5144193/review_all")) {
        /*we use jsoup to parse page*/
        Document doc = page.doc();

        /*extract title and content of news by css selector*/
        String title = page.select("div[class=review-truncated-words Hide]").first().text();
        
        
        System.out.println("title:\n" + title);

        /*If you want to add urls to crawl,add them to nextLink*/
        /*WebCollector automatically filters links that have been fetched before*/
        /*If autoParse is true and the link you add to nextLinks does not 
          match the regex rules,the link will also been filtered.*/
        //next.add("http://xxxxxx.com");
    }
    }

    public static void main(String[] args) throws Exception {
    NewsCrawler crawler = new NewsCrawler("crawl", true);
    crawler.addSeed("http://www.dianping.com/");
    crawler.addRegex("https://www.dianping.com/shop/5144193/review_all/.*");
    crawler.setThreads(50);
    crawler.getConf().setTopN(100);
    //crawler.setResumable(true);
    /*start crawl with depth of 4*/
    crawler.start(2);
    }

}