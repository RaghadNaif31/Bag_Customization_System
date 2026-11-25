/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//------------------------------------------------------------------------------
/**
 * Main application window for the Bag customization store.
 * Uses CardLayout to keep shopping, checkout, and tracking separate.
 */
public class Bag_Customization_System extends JFrame {
    private static final Color WINE = new Color(112, 30, 51);
    private static final Color CREAM = new Color(252, 247, 241);
    private static final Color INK = new Color(67, 42, 44);
    private static final Color GOLD = new Color(184, 137, 78);
    private static final String[] MODELS = {"Tote Bag", "Laptop Bag", "Backpack",
        "Crossbody Bag", "Travel Bag", "Shoulder Bag"};
    private static final int[] PRICES = {120, 180, 160, 140, 220, 150};
    private static final String[] IMAGE_FILES = {
        "tote.png", "laptop.png", "backpack.png",
        "crossbody.png", "travel.png", "shoulder.png"
    };
    private final ShoppingCart cart = new ShoppingCart();
    private final CheckoutFacade checkout = new CheckoutFacade();
    private final NotificationCenter notifications = new NotificationCenter();
    private final List<Order> orders = new ArrayList<>();
    private final CardLayout pages = new CardLayout();
    private final JPanel content = new JPanel(pages);
    private final JComboBox<String> colorBox = new JComboBox<>(new String[]{"Black", "Beige", "Brown", "Burgundy"});
    private final JComboBox<String> sizeBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
    private final JComboBox<String> materialBox = new JComboBox<>(new String[]{"Canvas", "Nylon", "Premium Leather"});
    private final JTextField initialsField = new JTextField(12);
    private final JCheckBox wrapBox = new JCheckBox("Gift wrapping (+10 SAR)");
    private final JCheckBox giftBox = new JCheckBox("Gift card (+5 SAR)");
    private final JLabel customPrice = new JLabel();
    private final JTextField customerName = new JTextField();
    private final JTextField customerPhone = new JTextField();
    private final JTextField customerAddress = new JTextField();
    private final JComboBox<String> paymentBox = new JComboBox<>(new String[]{"Card (demo)", "Cash on Delivery"});
    private final JPanel cartItems = new JPanel();
    private final JPanel cartSummary = new JPanel();
    private final JPanel checkoutSummary = new JPanel();
    private final JPanel orderCards = new JPanel();
    private final JPanel noticeCards = new JPanel();
    private String selectedModel = MODELS[0];
    private final JLabel selectedBagImage = new JLabel();

    //--------------------------------------------------------------------------
    /** Initializes the fixed-size store window and navigation. */
    public Bag_Customization_System() {
        super("Bag | Customize Your Style");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(920, 600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(CREAM);
        setLayout(new BorderLayout());
        add(sidebar(), BorderLayout.WEST);
        content.setBackground(CREAM);
        content.add(homePage(), "home");
        content.add(collectionPage(), "collection");
        content.add(customizationPage(), "customize");
        content.add(cartPage(), "cart");
        content.add(checkoutPage(), "checkout");
        content.add(ordersPage(), "orders");
        content.add(notificationsPage(), "notifications");
        add(content, BorderLayout.CENTER);
        updateSelectedBagImage();
        showPage("home");
    }

    //--------------------------------------------------------------------------
    /** Creates the persistent navigation sidebar. */
    private JPanel sidebar() {
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(190, 0));
        side.setBackground(WINE);
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBorder(new EmptyBorder(26, 14, 20, 14));
        JLabel brand = new JLabel("BAG", SwingConstants.CENTER);
        brand.setFont(new Font("Serif", Font.BOLD, 44));
        brand.setForeground(new Color(242, 211, 161));
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        side.add(brand);
        JLabel tagline = new JLabel("YOUR STYLE, YOUR BAG");
        tagline.setFont(new Font("SansSerif", Font.BOLD, 12));
        tagline.setForeground(new Color(242, 211, 161));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);
        side.add(tagline);
        side.add(Box.createVerticalStrut(42));
        side.add(sideButton("Home", () -> showPage("home")));
        side.add(sideButton("Collection", () -> showPage("collection")));
        side.add(sideButton("My Cart", () -> showPage("cart")));
        side.add(sideButton("My Orders", () -> showPage("orders")));
        side.add(sideButton("Notifications", () -> showPage("notifications")));
        side.add(Box.createVerticalGlue());
        JLabel footer = new JLabel("A little luxury, every day");
        footer.setForeground(new Color(233, 197, 176));
        footer.setFont(new Font("Serif", Font.ITALIC, 12));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        side.add(footer);
        return side;
    }

    //--------------------------------------------------------------------------
    private JButton sideButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(148, 42));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setForeground(Color.WHITE);
        button.setBackground(WINE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 14, 10, 8));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.addActionListener(event -> action.run());
        return button;
    }

    //--------------------------------------------------------------------------
    private JButton button(String text, Runnable action) {
        JButton result = new JButton(text);
        result.setFont(new Font("SansSerif", Font.BOLD, 16));
        result.setBackground(WINE);
        result.setForeground(Color.WHITE);
        result.setFocusPainted(false);
        result.setBorder(new EmptyBorder(12, 18, 12, 18));
        result.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        result.addActionListener(event -> action.run());
        return result;
    }

    //--------------------------------------------------------------------------
    private JLabel heading(String text, int size) {
        JLabel label = new JLabel(text);
        label.setForeground(INK);
        label.setFont(new Font("Serif", Font.BOLD, size));
        return label;
    }

    //--------------------------------------------------------------------------
    private JPanel page(String title) {
        JPanel page = backgroundPage(new BorderLayout(12, 16));
        page.setBorder(new EmptyBorder(25, 28, 24, 28));
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(252, 247, 241));
        top.setBorder(new EmptyBorder(12, 18, 12, 18));
        top.add(heading(title, 30), BorderLayout.WEST);
        if ("Our Collection".equals(title) || "Customize Your Bag".equals(title)) {
            top.add(button("View Cart", () -> showPage("cart")), BorderLayout.EAST);
        }
        page.add(top, BorderLayout.NORTH);
        return page;
    }

    //--------------------------------------------------------------------------
    private void showPage(String name) {
        switch (name) {
            case "cart":
                refreshCart();
                break;
            case "checkout":
                refreshCheckout();
                break;
            case "orders":
                refreshOrders();
                break;
            case "notifications":
                refreshNotifications();
                break;
            default:
                break;
        }
        pages.show(content, name);
    }

    //--------------------------------------------------------------------------
    /** Creates a page that displays the store background image. */
    private JPanel backgroundPage(LayoutManager layout) {
        return new BackgroundPanel(layout);
    }

    //--------------------------------------------------------------------------
    /** The welcome screen intentionally has one clear shopping action. */ 
    private JPanel homePage() {
    JPanel page = backgroundPage(new GridBagLayout());

    JPanel welcome = new JPanel();
    welcome.setOpaque(false);
    welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));
    welcome.setBorder(new EmptyBorder(35, 55, 35, 55));

    JLabel small = new JLabel("THE BAG COLLECTION");
    small.setFont(new Font("SansSerif", Font.BOLD, 17));
    small.setForeground(GOLD);
    small.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcome.add(small);

    welcome.add(Box.createVerticalStrut(22));

    JLabel title = heading("Welcome to BAG", 52);
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcome.add(title);

    welcome.add(Box.createVerticalStrut(20));

    JLabel subtitle = new JLabel(
            "Find the perfect bag. Make it yours."
    );
    subtitle.setFont(new Font("SansSerif", Font.PLAIN, 23));
    subtitle.setForeground(INK);
    subtitle.setHorizontalAlignment(SwingConstants.CENTER);
    subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcome.add(subtitle);

    welcome.add(Box.createVerticalStrut(38));

    JButton explore = button(
            "Explore Collection  →",
            () -> showPage("collection")
    );
    explore.setFont(new Font("SansSerif", Font.BOLD, 19));
    explore.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcome.add(explore);

    page.add(welcome);

    return page;
    }


    //--------------------------------------------------------------------------
    /** Displays all six models as a compact product collection. */
    private JPanel collectionPage() {
        JPanel page = page("Our Collection");
        JPanel grid = new JPanel(new GridLayout(2, 3, 16, 16));
        grid.setOpaque(false);
        for (int i = 0; i < MODELS.length; i++) {
            final int index = i;
            JPanel product = new JPanel(new BorderLayout(0, 6));
            product.setBackground(Color.WHITE);
            product.setBorder(new EmptyBorder(9, 9, 10, 9));
            // Load the matching PNG instead of drawing a bag.
            product.add(productImage(MODELS[i], 225, 135), BorderLayout.CENTER);
            JPanel details = new JPanel(new BorderLayout());
            details.setOpaque(false);
            details.add(heading(MODELS[i], 16), BorderLayout.NORTH);
            JLabel price = new JLabel("SAR " + PRICES[i]);
            price.setForeground(WINE);
            price.setFont(new Font("SansSerif", Font.BOLD, 16));
            price.setBorder(new EmptyBorder(8, 0, 10, 0));
            details.add(price, BorderLayout.CENTER);
            JButton select = button("Customize", () -> {
                selectedModel = MODELS[index];
                updateSelectedBagImage();
                resetCustomization();
                showPage("customize");
            });
            details.add(select, BorderLayout.SOUTH);
            product.add(details, BorderLayout.SOUTH);
            grid.add(product);
        }
        page.add(grid, BorderLayout.CENTER);
        return page;
    }

    //--------------------------------------------------------------------------
    /** Customization is separate from the collection, with a live price only. */
    private JPanel customizationPage() {
        JPanel page = page("Customize Your Bag");
        JPanel body = new JPanel(new GridLayout(1, 2, 22, 0));
        body.setOpaque(false);
        selectedBagImage.setHorizontalAlignment(SwingConstants.CENTER);
        selectedBagImage.setVerticalAlignment(SwingConstants.CENTER);
        selectedBagImage.setOpaque(false);
        body.add(selectedBagImage);
        JPanel fields = new JPanel();
        fields.setBackground(new Color(252, 247, 241));
        fields.setBorder(new EmptyBorder(18, 20, 18, 20));
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        fields.add(formRow("Color", colorBox));
        fields.add(formRow("Size", sizeBox));
        fields.add(formRow("Material", materialBox));
        fields.add(formRow("Initials (0-10 letters)", initialsField));
        fields.add(Box.createVerticalStrut(8));
        wrapBox.setOpaque(false);
        giftBox.setOpaque(false);
        wrapBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        giftBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        fields.add(wrapBox);
        fields.add(giftBox);
        fields.add(Box.createVerticalStrut(16));
        customPrice.setFont(new Font("Serif", Font.BOLD, 22));
        customPrice.setForeground(WINE);
        fields.add(customPrice);
        fields.add(Box.createVerticalStrut(16));
        fields.add(button("Add to Cart", this::addCustomizedBag));
        fields.add(Box.createVerticalStrut(8));
        fields.add(button("Back to Collection", () -> showPage("collection")));
        fields.add(Box.createVerticalGlue());
        body.add(fields);
        page.add(body, BorderLayout.CENTER);
        registerPriceListeners();
        initialsField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateCustomPrice(); }
            public void removeUpdate(DocumentEvent e) { updateCustomPrice(); }
            public void changedUpdate(DocumentEvent e) { updateCustomPrice(); }
        });
        return page;
    }

    //--------------------------------------------------------------------------
    /** Updates the displayed price when customization options change. */
    private void registerPriceListeners() {
        JComboBox<?>[] choices = {colorBox, sizeBox, materialBox};

        for (JComboBox<?> choice : choices) {
            choice.addActionListener(event -> updateCustomPrice());
        }

        JCheckBox[] extras = {wrapBox, giftBox};

        for (JCheckBox extra : extras) {
            extra.addActionListener(event -> updateCustomPrice());
        }
    }

    //--------------------------------------------------------------------------
    private JPanel formRow(String title, JComponent input) {
        JPanel row = new JPanel(new BorderLayout(0, 5));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 83));
        JLabel label = new JLabel(title);
        label.setForeground(INK);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        row.add(label, BorderLayout.NORTH);
        input.setPreferredSize(new Dimension(240, 38));
        input.setFont(new Font("SansSerif", Font.PLAIN, 16));
        row.add(input, BorderLayout.CENTER);
        row.setBorder(new EmptyBorder(0, 0, 9, 0));
        return row;
    }

    //--------------------------------------------------------------------------
    private void resetCustomization() {
        colorBox.setSelectedItem("Black");
        sizeBox.setSelectedItem("Medium");
        materialBox.setSelectedItem("Canvas");
        initialsField.setText("");
        wrapBox.setSelected(false);
        giftBox.setSelected(false);
        updateCustomPrice();
    }

    //--------------------------------------------------------------------------
    private Bag buildSelection() {
        Bag bag = new BagBuilder(selectedModel)
                .color((String) colorBox.getSelectedItem())
                .size((String) sizeBox.getSelectedItem())
                .material((String) materialBox.getSelectedItem())
                .initials(initialsField.getText())
                .build();
        if (wrapBox.isSelected()) {
            bag = new GiftWrapDecorator(bag);
        }

        if (giftBox.isSelected()) {
            bag = new GiftCardDecorator(bag);
        }
        return bag;
    }

    //--------------------------------------------------------------------------
    private void updateCustomPrice() {
        try {
            customPrice.setText(String.format("Total: SAR %.2f", buildSelection().getPrice()));
        } catch (IllegalArgumentException exception) {
            customPrice.setText("Enter up to 10 English letters.");
        }
    }

    //--------------------------------------------------------------------------
    private void addCustomizedBag() {
        try {
            cart.add(buildSelection());
            JOptionPane.showMessageDialog(this, "Added to your cart!");
            showPage("cart");
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Check your options", JOptionPane.WARNING_MESSAGE);
        }
    }

    //--------------------------------------------------------------------------
    /** Cart page has compact item rows and a separate total summary. */
    private JPanel cartPage() {
        JPanel page = page("Your Cart");
        JPanel body = new JPanel(new BorderLayout(15, 0));
        body.setOpaque(false);
        cartItems.setLayout(new BoxLayout(cartItems, BoxLayout.Y_AXIS));
        cartItems.setBackground(CREAM);
        JScrollPane scroll = new JScrollPane(cartItems);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREAM);
        body.add(scroll, BorderLayout.CENTER);
        cartSummary.setLayout(new BoxLayout(cartSummary, BoxLayout.Y_AXIS));
        cartSummary.setBackground(Color.WHITE);
        cartSummary.setBorder(new EmptyBorder(20, 16, 20, 16));
        cartSummary.setPreferredSize(new Dimension(235, 0));
        body.add(cartSummary, BorderLayout.EAST);
        page.add(body, BorderLayout.CENTER);
        return page;
    }

    //--------------------------------------------------------------------------
    private void refreshCart() {
        cartItems.removeAll();
        if (cart.isEmpty()) cartItems.add(heading("Your cart is empty.", 19));
        for (int i = 0; i < cart.getItems().size(); i++) {
            final int index = i;
            Bag bag = cart.getItems().get(i);
            JPanel item = new JPanel(new BorderLayout(12, 4));
            item.setBackground(Color.WHITE);
            item.setBorder(new EmptyBorder(13, 14, 13, 14));
            JPanel info = new JPanel(new GridLayout(3, 1));
            info.setOpaque(false);
            info.add(heading(bag.getModel(), 17));
            info.add(new JLabel(bag.getDescription()));
            info.add(new JLabel(String.format("SAR %.2f", bag.getPrice())));
            item.add(info, BorderLayout.CENTER);
            item.add(button("Remove", () -> {
                cart.remove(index);
                showPage("cart");
            }), BorderLayout.EAST);
            item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 115));
            cartItems.add(item);
            cartItems.add(Box.createVerticalStrut(10));
        }
        cartSummary.removeAll();
        addOrderSummary(cartSummary, false);
        cartSummary.add(button("Proceed to Checkout", () -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add a bag first.");
            } else {
                showPage("checkout");
            }
        }));
        cartSummary.add(Box.createVerticalStrut(12));
        cartSummary.add(button("Continue Shopping", () -> showPage("collection")));
        refreshPanel(cartItems);
        refreshPanel(cartSummary);
    }

    //--------------------------------------------------------------------------
    /** Checkout contains only delivery, payment, and the final order summary. */
    private JPanel checkoutPage() {
        JPanel page = page("Checkout");
        JPanel columns = new JPanel(new GridLayout(1, 3, 14, 0));
        columns.setOpaque(false);
        JPanel delivery = whitePanel();
        delivery.add(heading("Delivery Details", 19));
        delivery.add(Box.createVerticalStrut(20));
        delivery.add(formRow("Full Name", customerName));
        delivery.add(formRow("Phone Number", customerPhone));
        delivery.add(formRow("Delivery Address", customerAddress));
        JPanel payment = whitePanel();
        payment.add(heading("Payment Method", 19));
        payment.add(Box.createVerticalStrut(20));
        payment.add(formRow("Select a method", paymentBox));
        JLabel demo = new JLabel("Card payment is simulated.");
        demo.setForeground(GOLD);
        payment.add(demo);
        checkoutSummary.setLayout(new BoxLayout(checkoutSummary, BoxLayout.Y_AXIS));
        checkoutSummary.setBackground(Color.WHITE);
        checkoutSummary.setBorder(new EmptyBorder(18, 15, 18, 15));
        columns.add(delivery);
        columns.add(payment);
        columns.add(checkoutSummary);
        page.add(columns, BorderLayout.CENTER);
        return page;
    }

    //--------------------------------------------------------------------------
    private JPanel whitePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(18, 15, 18, 15));
        return panel;
    }

    //--------------------------------------------------------------------------
    private void refreshCheckout() {
        checkoutSummary.removeAll();
        addOrderSummary(checkoutSummary, true);
        checkoutSummary.add(button("Place Order", this::placeOrder));
        checkoutSummary.add(Box.createVerticalStrut(10));
        checkoutSummary.add(button("Back to Cart", () -> showPage("cart")));
        refreshPanel(checkoutSummary);
    }

    //--------------------------------------------------------------------------
    /** Adds the same price breakdown to the cart and checkout pages. */
    private void addOrderSummary(JPanel target, boolean showItemCount) {
        target.add(heading("Order Summary", 20));
        target.add(Box.createVerticalStrut(20));

        if (showItemCount) {
            target.add(new JLabel("Items: " + cart.getItems().size()));
            target.add(Box.createVerticalStrut(12));
        }

        target.add(new JLabel(String.format("Subtotal: SAR %.2f", cart.getSubtotal())));
        target.add(Box.createVerticalStrut(12));
        target.add(new JLabel(String.format("VAT (15%%): SAR %.2f", checkout.calculateVat(cart))));
        target.add(Box.createVerticalStrut(12));
        target.add(new JLabel(String.format("Delivery: SAR %.2f",
                cart.isEmpty() ? 0.0 : StoreConfig.getInstance().getShippingFee())));
        target.add(Box.createVerticalStrut(20));
        target.add(heading(String.format("Total: SAR %.2f", checkout.calculateTotal(cart)), 24));
        target.add(Box.createVerticalStrut(25));
    }

    //--------------------------------------------------------------------------
    private void placeOrder() {
        if (customerName.getText().trim().isEmpty()
                || !customerPhone.getText().trim().matches("[0-9+ ]{8,15}")
                || customerAddress.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter your name, valid phone, and address.",
                    "Delivery details required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            PaymentStrategy strategy = paymentBox.getSelectedIndex() == 0
                    ? new CardPayment() : new CashOnDelivery();
            Order order = checkout.placeOrder(cart, strategy);
            order.addObserver(notifications);
            notifications.addPlacedNotice(order);
            orders.add(order);
            JOptionPane.showMessageDialog(this, "Order #" + order.getId() + " placed successfully!");
            showPage("orders");
        } catch (IllegalStateException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    /** Orders display their four statuses; status changes are a demo control. */
    private JPanel ordersPage() {
        JPanel page = page("My Orders");
        orderCards.setLayout(new BoxLayout(orderCards, BoxLayout.Y_AXIS));
        orderCards.setBackground(CREAM);
        JScrollPane scroll = new JScrollPane(orderCards);
        scroll.setBorder(null);
        page.add(scroll, BorderLayout.CENTER);
        return page;
    }

    //--------------------------------------------------------------------------
    private void refreshOrders() {
        orderCards.removeAll();
        if (orders.isEmpty()) orderCards.add(heading("No orders yet. Start shopping!", 19));
        for (Order order : orders) {
            JPanel card = whitePanel();
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
            card.add(heading("Order #" + order.getId() + "   |   SAR "
                    + String.format("%.2f", order.getTotal()), 18));
            card.add(Box.createVerticalStrut(10));
            card.add(new JLabel("Status: " + order.getStatus()));
            card.add(Box.createVerticalStrut(10));
            JButton demo = button("Advance Order Status", () -> {
                try {
                    order.advanceStatus();
                    refreshOrders();
                } catch (IllegalStateException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            });
            demo.setEnabled(!"Delivered".equals(order.getStatus()));
            card.add(demo);
            orderCards.add(card);
            orderCards.add(Box.createVerticalStrut(12));
        }
        refreshPanel(orderCards);
    }

    //--------------------------------------------------------------------------
    private JPanel notificationsPage() {
        JPanel page = page("Notifications");
        noticeCards.setLayout(new BoxLayout(noticeCards, BoxLayout.Y_AXIS));
        noticeCards.setBackground(CREAM);
        JScrollPane scroll = new JScrollPane(noticeCards);
        scroll.setBorder(null);
        page.add(scroll, BorderLayout.CENTER);
        return page;
    }

    //--------------------------------------------------------------------------
    private void refreshNotifications() {
        noticeCards.removeAll();
        if (notifications.getMessages().isEmpty()) {
            noticeCards.add(heading("No notifications yet.", 19));
        }
        for (String message : notifications.getMessages()) {
            JLabel item = new JLabel("  " + message);
            item.setOpaque(true);
            item.setBackground(Color.WHITE);
            item.setForeground(INK);
            item.setBorder(new EmptyBorder(15, 12, 15, 12));
            item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
            noticeCards.add(item);
            noticeCards.add(Box.createVerticalStrut(10));
        }
        refreshPanel(noticeCards);
    }

    //--------------------------------------------------------------------------
    /** Refreshes a panel after its dynamic content changes. */
    private void refreshPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    //--------------------------------------------------------------------------
    /** Returns the PNG file name for a catalog model. */
    private String imageName(String model) {
        for (int index = 0; index < MODELS.length; index++) {
            if (MODELS[index].equals(model)) {
                return IMAGE_FILES[index];
            }
        }

        return "";
    }

    //--------------------------------------------------------------------------
    /**
     * Reads a PNG from src/images, matching the user's NetBeans folder layout.
     * Also supports src/main/resources/images when running a packaged project.
     */
    private static BufferedImage loadImage(String fileName) {
        String[] locations = {
            "src/images/" + fileName,
            "images/" + fileName,
            "src/main/resources/images/" + fileName
        };

        for (String location : locations) {
            File file = new File(location);
            if (file.isFile()) {
                try {
                    return ImageIO.read(file);
                } catch (Exception exception) {
                    System.err.println("Could not read image: " + location);
                }
            }
        }

        URL resource = Bag_Customization_System.class.getResource("/images/" + fileName);
        if (resource != null) {
            try {
                return ImageIO.read(resource);
            } catch (Exception exception) {
                System.err.println("Could not read resource: " + fileName);
            }
        }

        System.err.println("Missing image: " + fileName);
        return null;
    }

    //--------------------------------------------------------------------------
    /** Scales a product photograph without stretching its proportions. */
    private static ImageIcon scaledImage(String fileName, int width, int height) {
        BufferedImage original = loadImage(fileName);
        if (original == null) return null;

        double ratio = Math.min((double) width / original.getWidth(),
                (double) height / original.getHeight());
        int scaledWidth = Math.max(1, (int) (original.getWidth() * ratio));
        int scaledHeight = Math.max(1, (int) (original.getHeight() * ratio));
        Image resized = original.getScaledInstance(scaledWidth, scaledHeight,
                Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

    //--------------------------------------------------------------------------
    /** Creates a product picture label with a clear missing-file message. */
    private JLabel productImage(String model, int width, int height) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(width, height));
        ImageIcon picture = scaledImage(imageName(model), width, height);
        if (picture != null) {
            label.setIcon(picture);
        } else {
            label.setText("Image not found: " + imageName(model));
            label.setForeground(WINE);
        }
        return label;
    }

    //--------------------------------------------------------------------------
    /** Refreshes the selected product photograph on the customization page. */
    private void updateSelectedBagImage() {
        ImageIcon picture = scaledImage(imageName(selectedModel), 335, 350);
        selectedBagImage.setIcon(picture);
        selectedBagImage.setText(picture == null
                ? "Image not found: " + imageName(selectedModel) : "");
    }

    //--------------------------------------------------------------------------
    /** Displays the store_background.png image behind a page's controls. */
    private static class BackgroundPanel extends JPanel {
        private final BufferedImage background = loadImage("store_background.png");

        BackgroundPanel(LayoutManager layout) {
            super(layout);
            setBackground(CREAM);
        }

        //--------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (background != null) {
                graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                // Soft cream overlay improves text contrast on patterned areas.
                Graphics2D overlay = (Graphics2D) graphics.create();
                overlay.setColor(new Color(252, 247, 241, 115));
                overlay.fillRect(0, 0, getWidth(), getHeight());
                overlay.dispose();
            }
        }
    }

    //--------------------------------------------------------------------------
    /** Starts the Java Swing application on the Event Dispatch Thread. */

    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

        Bag_Customization_System app =
                new Bag_Customization_System();

        app.setVisible(true);
    });
   
    }
}
