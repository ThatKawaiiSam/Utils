package io.github.thatkawaiisam.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class BookUtility {

    /* Title and Author of the book */
    private String title, author;
    /* Pages of the book */
    private List<String> pages;

    /**
     * Set the Title
     */
    public BookUtility title(String title){
        this.title = title;
        return this;
    }

    /**
     * Set the Author
     */
    public BookUtility author(String author){
        this.author = author;
        return this;
    }

    /**
     * Set the Pages
     */
    public BookUtility pages(List<String> pages){
        this.pages = pages;
        return this;
    }

    /**
     * Build to an ItemStack
     */
    public ItemStack build(){
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        if (author != null)
            bookMeta.setAuthor(author);
        if (title != null)
            bookMeta.setTitle(title);
        if (pages != null)
            pages.forEach(string -> bookMeta.addPage(MessageUtility.formatMessage("&r" + string)));
        book.setItemMeta(bookMeta);
        return book;
    }
}
