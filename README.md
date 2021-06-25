# Datamuse4J

## What Is It
Datamuse4J is a handler for calling the [Datamuse RESTful API](https://www.datamuse.com/api/). Some background 
information on Datamuse:

> The Datamuse API is a word-finding query engine for developers. You can use it in your apps to find words that match a 
> given set of constraints and that are likely in a given context. You can specify a wide variety of constraints on 
> meaning, spelling, sound, and vocabulary in your queries, in any combination.
>
> The API gives you programmatic access to most of the functionality of Datamuse's websites.

Please note I am not affiliated with OneLook or Datamuse in any way. I just like the service and decided to
implement some Java code to allow easy access to the API.

## The API
All the available RESTful API calls have been implemented here for Java. If I have forgot to cover something you can 
probably fix it pretty easily yourself.

The endpoints used by Datamuse are `api.datamuse.com/words` and `api.datamuse.com/words`; these can handle both HTTP and 
HTTPS requests.

If you use the API within a publicly available app, kindly acknowledge the Datamuse API within your app's documentation.

### Rate Limiting
You can use the Datamuse service without restriction and without an API key for up to 100,000 requests per day. Please 
be aware that beyond that limit, requests may be rate-limited without notice. If you'd like to use this in a 
customer-facing application, or if you need a custom vocabulary, or if you plan to make more than 100,000 requests per 
day, please describe your application (and a traffic estimate) in a message to 
[Datamuse](https://www.onelook.com/?c=about&sel=api#feedback).

### Summary
Here is a brief summary of what this code can do (See the [Javadoc](javadoc/overview-tree.html) for full information):

**GET** `/words{?rd,sl,sp,max}`

**rd** `.findSimilar` “Reverse dictionary” constraint: show terms that have a meaning similar to this concept, which may 
be any word or sequence of words.

**sl** `.soundsSimilar` “Sounds like” constraint: show terms that are pronounced similarly to this string of characters.

**sp** `.speltSimilar` “Spelled like” constraint: shows terms that are spelled similarly to this string of characters, 
or that match this wildcard pattern.

**GET** `/sug{?s,max}`

**s** `.prefixHintSuggestions` Prefix hint string; typically, the characters that the user has entered so far into a 
search box. (Note: The results are sorted by popularity and may include spell-corrections of the prefix hint. That is to 
say, the prefix hint will not necessarily form a prefix of each result.)
