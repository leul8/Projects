import re
import math
import matplotlib.pyplot as plt
import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QLineEdit, QPushButton, QTextEdit
from collections import Counter

paths = ['1.txt', '2.txt', '3.txt','4.txt', '5.txt', '6.txt','7.txt', '8.txt', '9.txt','10.txt', '11.txt', '12.txt','13.txt', '14.txt']
texts = []
for files in paths:
    with open(files, 'r', encoding='utf-8') as file:
        text = file.read()
        texts.append(text)
def tokenizeword(texts):

     tokenized_words = []

     for line in texts:
         line = re.sub('[^\u1200-\u137F]', ' ', line)
         line = re.sub('[፩፪፫፬፭፮፯፰፱፲፳፻፷፹፸፴፵፶፺]', '', line)
         line = re.sub('[፡።፣፤፥፦፨]', ' ', line)
         for words in line.split():
             tokenized_words.append(words)

     return tokenized_words

def CountFrequencyandrank(tokenized_words):
    Count1 = 0
    freq = dict()
    for word in tokenized_words:
        if word in freq:
            freq[word] = freq[word] + 1
        else:
            freq[word] = 1
    sorted_words = sorted(freq.items(), key=lambda x: x[1], reverse=True)

    return sorted_words

def plottinggraph():
    word_frequency = Counter(tokenizeword(texts))
    sorted_word_frequency = sorted(word_frequency.items(), key=lambda x: x[1], reverse=True)
    ranks = range(1, len(sorted_word_frequency) + 1)
    frequencies = [pair[1] for pair in sorted_word_frequency]
    plt.figure(figsize=(10, 6))
    plt.plot(ranks, frequencies, marker='o', linestyle='-')
    plt.xlabel('Rank')
    plt.ylabel('Frequency')
    plt.title('Frequency vs. Rank Plot')
    plt.xscale('log')
    plt.yscale('log')
    plt.grid(True)
    plt.show()

def product():
    words = tokenizeword(texts)
    word_counts = Counter(words)
    sorted_words = sorted(word_counts.items(), key=lambda x: x[1], reverse=True)
    for rank, (word, frequency) in enumerate(sorted_words, start = 1):
        product = rank * frequency
        print(f"Rank {rank}: '{word}' * Frequency: {frequency} = Product: {product}")


def luhn_indexing(data):
    tokenized_words = tokenizeword(data)
    min_freq = 2
    max_freq_ratio = 0.1
    word_freq = Counter(tokenized_words)
    total_words = len(tokenized_words)
    upper_cutoff = max_freq_ratio * total_words
    indexing_words = [
        word for word,
        freq in word_freq.items()
        if min_freq <= freq <= upper_cutoff
    ]
    temp = []
    for word in indexing_words:
        temp.append(word)
    return indexing_words

def stopwords(File):
    indexw = []
    stopwords1 = open("stopwords-am.txt",encoding='utf-8')
    stopwords2 = []
    for line1 in stopwords1:
        for words1 in line1.split():
            stopwords2.append(words1)
    #print(stopwords2)
    for line2 in File:
        for words2 in line2.split():
            if words2 not in stopwords2:
                indexw.append(words2)
    return indexw
def termfrequency(File):
    tf = Counter(tokenizeword(File))
    return tf

#print(tokenizeword(texts))
#print(len(tokenizeword(texts)))
#print(CountFrequencyandrank(tokenizeword(texts)))
#plottinggraph()
#print(product())
#print(find_cutoff_points_and_indexes())
class InvertedIndexVSM:
    def __init__(self):
        self.index = {}
        self.doc_lengths = {}

    def add_document(self, doc_id, terms):
        # Update the inverted index
        for term in terms:
            if term not in self.index:
                self.index[term] = {}
            if doc_id not in self.index[term]:
                self.index[term][doc_id] = 0
            self.index[term][doc_id] += 1

        # Update document lengths
        self.doc_lengths[doc_id] = sum((1 + math.log(tf)) ** 2 for tf in terms.values())

    def search(self, query):
        query_vector = self._create_query_vector(query)
        scores = {}

        # Compute cosine similarity between query vector and document vectors
        for term, query_weight in query_vector.items():
            if term in self.index:
                for doc_id, doc_weight in self.index[term].items():
                    if doc_id not in scores:
                        scores[doc_id] = 0
                    scores[doc_id] += query_weight * doc_weight / math.sqrt(self.doc_lengths[doc_id])

        # Sort documents by relevance
        sorted_docs = sorted(scores.items(), key=lambda x: x[1], reverse=True)
        return [doc_id for doc_id, _ in sorted_docs]

    def _create_query_vector(self, query):
        query_terms = Counter(query.split())
        max_freq = max(query_terms.values())
        query_vector = {}
        for term, freq in query_terms.items():
            query_vector[term] = (1 + math.log(freq)) / max_freq
        return query_vector


index = InvertedIndexVSM()

def normalize(input_token):
    s = " "
    s = s.join(input_token)
    rep1 = re.sub('[ሃኅኃሐሓኻ]', 'ሀ', s)
    rep2 = re.sub('[ሑኁዅ]', 'ሁ', rep1)
    rep3 = re.sub('[ኂሒኺ]', 'ሂ', rep2)
    rep4 = re.sub('[ኌሔዄ]', 'ሄ', rep3)
    rep5 = re.sub('[ሕኅ]', 'ህ', rep4)
    rep6 = re.sub('[ኆሖኾ]', 'ሆ', rep5)
    rep7 = re.sub('[ሠ]', 'ሰ', rep6)
    rep8 = re.sub('[ሡ]', 'ሱ', rep7)
    rep9 = re.sub('[ሢ]', 'ሲ', rep8)
    rep10 = re.sub('[ሣ]', 'ሳ', rep9)
    rep11 = re.sub('[ሤ]', 'ሴ', rep10)
    rep12 = re.sub('[ሥ]', 'ስ', rep11)
    rep13 = re.sub('[ሦ]', 'ሶ', rep12)
    rep14 = re.sub('[ዓኣዐ]', 'አ', rep13)
    rep15 = re.sub('[ዑ]', 'ኡ', rep14)
    rep16 = re.sub('[ዒ]', 'ኢ', rep15)
    rep17 = re.sub('[ዔ]', 'ኤ', rep16)
    rep18 = re.sub('[ዕ]', 'እ', rep17)
    rep19 = re.sub('[ዖ]', 'ኦ', rep18)
    rep20 = re.sub('[ጸ]', 'ፀ', rep19)
    rep21 = re.sub('[ጹ]', 'ፁ', rep20)
    rep22 = re.sub('[ጺ]', 'ፂ', rep21)
    rep23 = re.sub('[ጻ]', 'ፃ', rep22)
    rep24 = re.sub('[ጼ]', 'ፄ', rep23)
    rep25 = re.sub('[ጽ]', 'ፅ', rep24)
    rep26 = re.sub('[ጾ]', 'ፆ', rep25)
    # Normalizing words with Labialized Amharic characters such as በልቱዋል or  በልቱአል to  በልቷል
    rep27 = re.sub('(ሉ[ዋአ])', 'ሏ', rep26)
    rep28 = re.sub('(ሙ[ዋአ])', 'ሟ', rep27)
    rep29 = re.sub('(ቱ[ዋአ])', 'ቷ', rep28)
    rep30 = re.sub('(ሩ[ዋአ])', 'ሯ', rep29)
    rep31 = re.sub('(ሱ[ዋአ])', 'ሷ', rep30)
    rep32 = re.sub('(ሹ[ዋአ])', 'ሿ', rep31)
    rep33 = re.sub('(ቁ[ዋአ])', 'ቋ', rep32)
    rep34 = re.sub('(ቡ[ዋአ])', 'ቧ', rep33)
    rep35 = re.sub('(ቹ[ዋአ])', 'ቿ', rep34)
    rep36 = re.sub('(ሁ[ዋአ])', 'ኋ', rep35)
    rep37 = re.sub('(ኑ[ዋአ])', 'ኗ', rep36)
    rep38 = re.sub('(ኙ[ዋአ])', 'ኟ', rep37)
    rep39 = re.sub('(ኩ[ዋአ])', 'ኳ', rep38)
    rep40 = re.sub('(ዙ[ዋአ])', 'ዟ', rep39)
    rep41 = re.sub('(ጉ[ዋአ])', 'ጓ', rep40)
    rep42 = re.sub('(ደ[ዋአ])', 'ዷ', rep41)
    rep43 = re.sub('(ጡ[ዋአ])', 'ጧ', rep42)
    rep44 = re.sub('(ጩ[ዋአ])', 'ጯ', rep43)
    rep45 = re.sub('(ጹ[ዋአ])', 'ጿ', rep44)
    rep46 = re.sub('(ፉ[ዋአ])', 'ፏ', rep45)
    rep47 = re.sub('[ቊ]', 'ቁ', rep46)  # ቁ can be written as ቊ
    rep48 = re.sub('[ኵ]', 'ኩ', rep47)  # ኩ can be also written as ኵ
    normlist = rep48.split()
    return normlist
# Define the suffix and prefix lists
suffix_list = "ኦችኣችኧውንንኣ|ኦችኣችህኡ|ኦችኣችኧውን|ኣችኧውንንኣ|ኦችኣችኧው|ኢዕኧልኧሽ|ኦችኣችን|ኣውኢው|ኣችኧውኣል|ችኣት|ችኣችህኡ|ችኣችኧው|ኣልኧህኡ|ኣውኦች|ኣልኧህ|ኣልኧሽ|ኣልችህኡ|ኣልኣልኧች|ብኣችኧውስ|ብኣችኧው|ኣችኧውን|ኣልኧች|ኣልኧን|ኣልኣችህኡ|ኣችህኡን|ኣችህኡ|ኣችህኡት|ውኦችንንኣ|ውኦችን|ኣችኧው|ውኦችኡን|ውኦችኡ|ውንኣ|ኦችኡን|ውኦች|ኝኣንኧትም|ኝኣንኣ|ኝኣንኧት|ኝኣን|ኝኣውም|ኝኣው|ኣውኣ|ብኧትን|ኣችህኡም|ችኣችን|ኦችህ|ኦችሽ|ኦችኡ|ኦችኤ|ኦውኣ|ኦቿ|ችው|ችኡ|ኤችኡ|ንኧው|ንኧት|ኣልኡ|ኣችን|ክኡም|ክኡት|ክኧው|ችን|ችም|ችህ|ችሽ|ችን|ችው|ይኡሽን|ይኡሽ|ውኢ|ኦችንንኣ|ኣውኢ|ብኧት|ኦች|ኦችኡ|ውኦን|ኝኣ|ኝኣውን|ኝኣው|ኦችን|ኣል|ም|ሽው|ክም|ኧው|ውኣ|ትም|ውኦ|ውም|ውን|ንም|ሽን|ኣች|ኡት|ኢት|ክኡ|ኤ|ህ|ሽ|ኡ|ሽ|ክ|ች|ኡን|ን|ም|ንኣ"
prefix_list = "ስልኧምኣይ|ይኧምኣት|ዕንድኧ|ይኧትኧ|ብኧምኣ|ብኧትኧ|ዕኧል|ስልኧ|ምኧስ|ዕይኧ|ይኣል|ስኣት|ስኣን|ስኣይ|ስኣል|ይኣስ|ይኧ|ልኧ|ብኧ|ክኧ|እን|አል|አስ|ትኧ|አት|አን|አይ|ይ|አ|እ"


def stem1(word):
    # Prepare suffix array
    sarr = suffix_list.split("|")
    sfx_arr = [(suffix, " ") for suffix in sarr]

    # Prepare prefix array
    parr = prefix_list.split("|")
    pfx_arr = [(prefix, " ") for prefix in parr]

    # Remove suffixes
    for sfx in sfx_arr:
        if word.endswith(sfx):
            word = word[:-len(sfx)]
            break

    # Remove prefixes
    for pfx in pfx_arr:
        if word.startswith(pfx):
            cv_string = word[len(pfx):]
            break

    # Remove infixes
    if any(word.endswith(char) for char in ['ሀ', 'ሐ', 'ሠ', 'ሸ', 'ቐ', 'በ', 'ተ', 'ኀ', 'አ', 'ኸ']):
        cv_string = word[:-1]
    elif any(word.endswith(char) for char in ['ነ', 'ገ', 'ጠ', 'ጰ', 'ጸ']):
        word = word[:-2]

    return word

def stem2(tokenized_words):
    stemmed_words = []
    for word in tokenized_words:
        stemmed_words.append(stem1(word))
    return stemmed_words
def preprocessing(num):
    for i in range(1,num + 1):
       File = open(str(i) +".txt",encoding='utf-8')
       index.add_document(i,termfrequency(stem2(stopwords(normalize(luhn_indexing(File))))))

preprocessing(14)
def tostring(list):
    w = ""
    for word in list:
        w += word
        w += " "
    return w
def SearchT(s):
    if index.search(tostring(tostring(stem2(stopwords(normalize((s.split()))))))):
        return index.search(tostring(stem2(stopwords(normalize((s.split()))))))
    else:
        return 0


class SearchGUI(QWidget):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Document Search")
        self.setGeometry(100, 100, 400, 300)
        self.initUI()

    def initUI(self):
        self.label = QLabel("Enter query:", self)
        self.label.move(20, 20)

        self.query_entry = QLineEdit(self)
        self.query_entry.move(120, 20)
        self.query_entry.resize(200, 25)

        self.search_button = QPushButton("Search", self)
        self.search_button.move(330, 20)
        self.search_button.clicked.connect(self.search)

        self.clear_button = QPushButton("Clear", self)
        self.clear_button.move(330, 50)
        self.clear_button.clicked.connect(self.clear)

        self.result_text = QTextEdit(self)
        self.result_text.move(20, 90)
        self.result_text.resize(360, 190)

    def search(self):
        query = self.query_entry.text()
        if query:
            results = SearchT(query)
            self.display_results(results)

    def display_results(self, results):
        i = 1
        self.result_text.clear()
        if results:
            self.result_text.append("Search results:")
            for result in results:
                self.result_text.append(f"{i}.Document {result}")
                i += 1
        else:
            self.result_text.append("No results found.")

    def clear(self):
        self.query_entry.clear()
        self.result_text.clear()

def main():
    app = QApplication(sys.argv)
    search_gui = SearchGUI()
    search_gui.show()
    sys.exit(app.exec_())

if __name__ == "__main__":
    main()