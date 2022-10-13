package com.radx.ankunv2.anime

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.eachText
import it.skrape.selects.html5.span
import it.skrape.selects.html5.ul

object AnimeSeasons {
    private const val baseUrl = "https://animension.to/"
    private var seasons: List<String> = emptyList()

    fun getSeasonsList(): List<String> {
        getSeasons()
        return this.seasons
    }

    private fun getSeasons() {
        return skrape(HttpFetcher) {
            request {
                url = baseUrl + "seasons"
            }

            response {
                htmlDocument {
                    ul {
                        withClass = "taxindex"
                        findFirst {
                            span {
                                withClass = "name"
                                findAll {
                                    seasons = eachText
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}