import random
from datetime import datetime, timedelta

members = ["소병희", "이지민", "장희직", "전현수"]
weightedMembers = []


def findLastPresenters():
    with open('README.md', 'r') as f:
        last_line = f.readlines()[-1]

    lastPresentersData = last_line.strip().split("|")

    return [lastPresentersData[2].strip(), lastPresentersData[3].strip()]


def getRandomPresenters():
    (lastFirstPresenter, lastSecondPresenter) = findLastPresenters()

    for member in members:

        if member == lastFirstPresenter:
            weight = 2
        elif member == lastSecondPresenter:
            weight = 3
        else:
            weight = 5

        for _ in range(weight):
            weightedMembers.append(member)

    random.shuffle(weightedMembers)
    firstPresenter = weightedMembers[0]

    filteredWeightedMembers = list(filter(lambda presenter: presenter != firstPresenter, weightedMembers))

    random.shuffle(filteredWeightedMembers)
    secondPresenter = filteredWeightedMembers[0]

    return [firstPresenter, secondPresenter]


presenters = getRandomPresenters()
print(weightedMembers)
result = (datetime.today() + timedelta(days=2)).strftime("| %Y년 %m년 %d일 | {}      | {}      |").format(presenters[0],
                                                                                                       presenters[1])
file = open("README.md", 'a')
file.write("{}\n".format(result))

file.close()
