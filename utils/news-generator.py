import requests
import json 

r = requests.get("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").text
ids = json.loads(r)
articles = []
for article_id in ids[:20]:
    url = "https://hacker-news.firebaseio.com/v0/item/{}.json?print=pretty".format(article_id)
    detail_r = requests.get(url).text
    article_json = json.loads(detail_r)
    if 'descendants' in article_json:
        del article_json["descendants"]
    if 'kids' in article_json:
        del article_json["kids"]
    articles.append(article_json)

articles = json.dumps(articles)
print(articles)

with open("hacker_news.json", "w") as text_file:
    text_file.write(articles)
