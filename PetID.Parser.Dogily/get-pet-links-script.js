//dog
var dogXPath = $x('//*[@id="wide-nav"]/div/div[2]/ul/li[3]/ul/li/ul/li/a/@href');
dogXPath = dogXPath.map(o=>o.textContent);
var dog = dogXPath.join('\n');
//cat
var catXPath = $x('//*[@id="wide-nav"]/div/div[2]/ul/li[4]/ul/li/a/@href');
catXPath = catXPath.map(o=>o.textContent);
var cat = catXPath.join('\n');