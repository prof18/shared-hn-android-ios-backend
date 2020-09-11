package com.prof18.hn.backend

import com.prof18.hn.dto.ListResponseDTO
import com.prof18.hn.dto.NewsDTO


class NewsRepositoryImpl : NewsRepository {

    override fun getTopStories(): ListResponseDTO<NewsDTO> {
        return ListResponseDTO(
                items = news.sortedByDescending { it.timestamp }
        )
    }

    private val news by lazy {
        listOf(
                NewsDTO(
                        author = "rbanffy",
                        id = 24401439,
                        score = 47,
                        timestamp = 1599503461,
                        title = "Arm Announces Cortex-R82: First 64-Bit Real Time Processor",
                        type = "story",
                        url = "https://www.anandtech.com/show/16056/arm-announces-cortexr82-first-64bit-real-time-processor"
                ),
                NewsDTO(
                        author = "tdhttt",
                        id = 24401462,
                        score = 29,
                        timestamp = 1599503654,
                        title = "One of quantum physics’ greatest paradoxes may have lost its leading explanation",
                        type = "story",
                        url = "https://www.sciencemag.org/news/2020/09/one-quantum-physics-greatest-paradoxes-may-have-lost-its-leading-explanation"
                ),
                NewsDTO(
                        author = "tomohawk",
                        id = 24401664,
                        score = 15,
                        timestamp = 1599504852,
                        title = "The US Army Spent Millions Developing Giant, Six-Legged Walking Trucks in 1980s",
                        type = "story",
                        url = "https://www.thedrive.com/news/36157/the-us-army-spent-millions-developing-giant-six-legged-walking-trucks-in-the-1980s"
                ),
                NewsDTO(
                        author = "pjmlp",
                        id = 24398469,
                        score = 19,
                        timestamp = 1599475969,
                        title = "Pony, Actors, Causality, Types, and Garbage Collection",
                        type = "story",
                        url = "https://www.infoq.com/presentations/pony-types-garbage-collection/"
                ),
                NewsDTO(
                        author = "praveenscience",
                        id = 24401398,
                        score = 12,
                        timestamp = 1599503133,
                        title = "Something strange happens on Mars during a solar eclipse",
                        type = "story",
                        url = "https://www.sciencealert.com/something-strange-happens-on-mars-during-a-solar-eclipse"
                ),
                NewsDTO(
                        author = "rbanffy",
                        id = 24399392,
                        score = 84,
                        timestamp = 1599486418,
                        title = "Calculating the sample size required for developing a clinical prediction model",
                        type = "story",
                        url = "https://www.bmj.com/content/368/bmj.m441/rr"
                ),
                NewsDTO(
                        author = "43t344efsg",
                        id = 24401565,
                        score = 6,
                        timestamp = 1599504272,
                        title = "Gary Marcus's Kafkaesque Critique of GPT-3",
                        type = "story",
                        url = "https://nostalgebraist.tumblr.com/post/628024664310136832/gary-marcus-has-co-authored-a-brief-critique-of"
                ),
                NewsDTO(
                        author = "based2",
                        id = 24390893,
                        score = 39,
                        timestamp = 1599395286,
                        title = "Simple Bugs with Complex Exploits",
                        type = "story",
                        url = "https://www.elttam.com/blog/simple-bugs-with-complex-exploits/"
                ),
                NewsDTO(
                        author = "alex_hirner",
                        id = 24382360,
                        score = 155,
                        timestamp = 1599296806,
                        title = "Geometric Algebra for Python",
                        type = "story",
                        url = "https://github.com/pygae/clifford"
                ),
                NewsDTO(
                        author = "dfgdghdf",
                        id = 24397772,
                        score = 9,
                        timestamp = 1599468440,
                        title = "The 10,000 Year Clock",
                        type = "story",
                        url = "https://longnow.org/clock/"
                ),
                NewsDTO(
                        author = "jairajs89",
                        id = 24400779,
                        score = 1,
                        timestamp = 1599498264,
                        title = "Substack (YC W18) Is Hiring to Build a Better Business Model for Writing",
                        type = "job",
                        url = "https://substack.com/jobs"
                ),
                NewsDTO(
                        author = "aaron-santos",
                        id = 24401085,
                        score = 7,
                        timestamp = 1599500646,
                        title = "Bash Pitfalls",
                        type = "story",
                        url = "https://mywiki.wooledge.org/BashPitfalls"
                ),
                NewsDTO(
                        author = "colinprince",
                        id = 24382953,
                        score = 9,
                        timestamp = 1599307162,
                        title = "Smart Watches Could Do More for Wheelchair Users",
                        type = "story",
                        url = "https://fivethirtyeight.com/features/smart-watches-could-do-more-for-wheelchair-users/"
                ),
                NewsDTO(
                        author = "panic",
                        id = 24386120,
                        score = 7,
                        timestamp = 1599333188,
                        title = "How can you make subjective time go slower?",
                        type = "story",
                        url = "http://theoryengine.org/life/tips-for-a-longer-life/"
                ),
                NewsDTO(
                        author = "mmerlin",
                        id = 24398485,
                        score = 73,
                        timestamp = 1599476163,
                        title = "800x480 Touchscreen Raspberry Pi 3B+ Hackable Linux Handheld",
                        type = "story",
                        url = "http://yarh.io/yarh-io-mki.html"
                ),
                NewsDTO(
                        author = "ycombinete",
                        id = 24390778,
                        score = 127,
                        timestamp = 1599393804,
                        title = "Does Storing Bread in the Fridge Make it Last Longer? (2019)",
                        type = "story",
                        url = "https://culinarylore.com/food-science:does-storing-bread-in-the-fridge-make-it-last-longer/"
                ),
                NewsDTO(
                        author = "jrhouston",
                        id = 24397111,
                        score = 3,
                        timestamp = 1599460888,
                        title = "Eliza, the Rogerian Therapist (1999)",
                        type = "story",
                        url = "http://psych.fullerton.edu/mbirnbaum/psych101/Eliza.htm"
                ),
                NewsDTO(
                        author = "kwhitefoot",
                        id = 24390855,
                        score = 98,
                        timestamp = 1599394809,
                        title = "Why it is Important that Software Projects Fail (2008)",
                        type = "story",
                        url = "https://www.berglas.org/Articles/ImportantThatSoftwareFails/ImportantThatSoftwareFails.html"
                ),
                NewsDTO(
                        author = "mindcrime",
                        id = 24396987,
                        score = 173,
                        timestamp = 1599459028,
                        title = "International Space Station 437.800 MHz cross band FM repeater activated",
                        type = "story",
                        url = "https://amsat-uk.org/2020/09/02/iss-fm-repeater-activated/"
                ),
                NewsDTO(
                        author = "maydemir",
                        id = 24397867,
                        score = 4,
                        timestamp = 1599469246,
                        title = "Withings' ScanWatch packs ECG and SpO2 sensors alongside the usual tools",
                        type = "story",
                        url = "https://www.engadget.com/withings-scanwatch-hands-on-ifa-2020-070001875.html"
                )
        )
    }

}

