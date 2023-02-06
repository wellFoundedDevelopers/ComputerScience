import random
from datetime import datetime, timedelta


def getRandomPresenters():
    members = ["소병희", "이지민", "장희직", "전현수"]
    random.shuffle(members)
    return [members[0], members[1]]


presenters = getRandomPresenters()

result = (datetime.today() + timedelta(days=1)).strftime("| %Y년 %m년 %d일 | {}      | {}      |").format(presenters[0],
                                                                                                       presenters[1])
file = open("README.md", 'a')
file.write("{}\n".format(result))

file.close()