package org.delcom.intermediate.classesandinterface

// Exercise 2 - Media Interface

// Interface definitions moved OUTSIDE of main()
interface Media {
    val title: String
    fun play()
}

interface EnhancedMedia : Media {
    val duration: Int // in seconds

    // Default implementation
    fun getDurationFormatted(): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return "$minutes:${seconds.toString().padStart(2, '0')}"
    }

    // Abstract method
    fun pause()
}

interface Downloadable {
    fun download()
    val fileSize: Long
}

interface Shareable {
    fun share(platform: String)
}

// Audio class
class Audio(override val title: String, val composer: String) : Media {
    override fun play() {
        println("Playing audio: $title, composed by $composer")
    }
}

// Video class
class Video(override val title: String, val director: String) : Media {
    override fun play() {
        println("Playing video: $title, directed by $director")
    }

    fun showSubtitles(language: String) {
        println("Showing subtitles in $language for: $title")
    }
}

// Podcast class
class Podcast(override val title: String, val host: String, val episode: Int) : Media {
    override fun play() {
        println("Playing podcast: $title")
        println("  Host: $host")
        println("  Episode: $episode")
    }
}

// EBook class
class EBook(override val title: String, val author: String, val pageCount: Int) : Media {
    override fun play() {
        println("Opening eBook: $title")
        println("  Author: $author")
        println("  Pages: $pageCount")
    }

    fun bookmark(page: Int) {
        println("Bookmarked page $page in: $title")
    }
}

// MusicTrack class implementing EnhancedMedia
class MusicTrack(
    override val title: String,
    val artist: String,
    override val duration: Int
) : EnhancedMedia {
    override fun play() {
        println("Now playing: $title by $artist")
        println("Duration: ${getDurationFormatted()}")
    }

    override fun pause() {
        println("Music paused: $title")
    }

    // Can override default implementation
    override fun getDurationFormatted(): String {
        return "🎵 ${super.getDurationFormatted()}"
    }
}

// DownloadableVideo class implementing multiple interfaces
class DownloadableVideo(
    override val title: String,
    override val fileSize: Long
) : Media, Downloadable, Shareable {
    override fun play() {
        println("Playing downloadable video: $title")
    }

    override fun download() {
        println("Downloading video: $title (${fileSize}MB)")
    }

    override fun share(platform: String) {
        println("Sharing video '$title' on $platform")
    }
}

fun main() {
    println("=== Media Interface Implementation ===\n")

    // Interface definition
    println("1. Interface Definition:")
    println("-".repeat(40))

    // Testing the implementation
    val audio = Audio("Symphony No. 5", "Beethoven")
    audio.play()

    println("\n" + "=".repeat(60) + "\n")

    // Understanding interfaces
    println("2. Understanding Interfaces:")
    println("-".repeat(40))

    println("Interface Media:")
    println("• Declares property: title (no implementation)")
    println("• Declares method: play() (no implementation)")
    println("• Cannot be instantiated directly")
    println("• Classes implement interface using ':'")

    println("\nClass Audio:")
    println("• Implements Media interface")
    println("• Overrides title property in constructor")
    println("• Implements play() method")
    println("• Adds its own property: composer")

    println("\nKey syntax:")
    println("  override val title: String  // In constructor")
    println("  override fun play() { ... } // Method implementation")

    println("\n" + "=".repeat(60) + "\n")

    // Implementing other media types
    println("3. Implementing Other Media Types:")
    println("-".repeat(40))

    // Testing all media types
    val video = Video("The Matrix", "The Wachowskis")
    val podcast = Podcast("Tech Talk", "Alex Developer", 42)
    val ebook = EBook("Kotlin Programming", "Jane Smith", 350)

    println("Testing Video:")
    video.play()
    video.showSubtitles("English")

    println("\nTesting Podcast:")
    podcast.play()

    println("\nTesting eBook:")
    ebook.play()
    ebook.bookmark(50)

    println("\n" + "=".repeat(60) + "\n")

    // Working with polymorphism
    println("4. Working with Polymorphism:")
    println("-".repeat(40))

    val mediaLibrary: List<Media> = listOf(audio, video, podcast, ebook)

    println("Playing all media in library:")
    println("-".repeat(30))

    mediaLibrary.forEach { media ->
        media.play()
        println() // Empty line
    }

    // Type checking and smart casting
    println("Checking media types:")
    println("-".repeat(30))

    for (media in mediaLibrary) {
        print("${media.title}: ")

        when (media) {
            is Audio -> {
                println("Audio by ${media.composer}")
            }
            is Video -> {
                println("Video directed by ${media.director}")
            }
            is Podcast -> {
                println("Podcast hosted by ${media.host}")
            }
            is EBook -> {
                println("EBook by ${media.author} (${media.pageCount} pages)")
            }
        }
    }

    println("\n" + "=".repeat(60) + "\n")

    // Interface with default implementations
    println("5. Interface with Default Implementations:")
    println("-".repeat(40))

    val track = MusicTrack("Bohemian Rhapsody", "Queen", 354)
    track.play()
    track.pause()
    println("Formatted duration: ${track.getDurationFormatted()}")

    // Multiple interface implementation
    println("\n6. Multiple Interface Implementation:")
    println("-".repeat(40))

    val downloadableVideo = DownloadableVideo("Kotlin Tutorial", 150)
    downloadableVideo.play()
    downloadableVideo.download()
    downloadableVideo.share("YouTube")

    // Demonstrate using the pause() function to fix the warning
    println("\nDemonstrating pause functionality:")
    track.pause()

    // Demonstrate using the fileSize property to fix the warning
    println("\nFile size details:")
    println("Video file size: ${downloadableVideo.fileSize}MB")
}