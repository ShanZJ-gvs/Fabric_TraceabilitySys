
Vue.config.productionTip = false
new Vue({
    el: '#topbar',
    data:{
        company1:document.getElementById("company").innerHTML
    },
    mounted () {
        function menu(){
            // Collapse
            const collapse = () => {
                const selector = '[data-toggle="collapse"]';

                // Toggle Collapse
                const toggleCollapse = (event) => {
                    const collapseTrigger = event.target;

                    collapseTrigger.classList.toggle("active");

                    // Open
                    const open = (collapse) => {
                        const collapseHeight = collapse.scrollHeight + "px";
                        setTimeout(() => {
                            collapse.style.height = collapseHeight;
                            collapse.style.opacity = 1;
                        }, 200);
                        animateNode(collapse, () => {
                            collapse.style.overflow = "visible";
                            collapse.style.height = null;
                            collapse.classList.add("open");
                        });
                    };

                    // Close
                    const close = (collapse) => {
                        collapse.style.overflow = "hidden";
                        const collapseHeight = collapse.scrollHeight + "px";
                        collapse.style.height = collapseHeight;
                        setTimeout(() => {
                            collapse.style.height = 0;
                            collapse.style.opacity = 0;
                        }, 200);
                        animateNode(collapse, () => {
                            collapse.classList.remove("open");
                        });
                    };

                    // Collapse
                    const collapses = document.querySelectorAll(collapseTrigger.dataset.target);
                    collapses.forEach((collapse) => {
                        if (collapse.classList.contains("open")) {
                            close(collapse);
                        } else {
                            open(collapse);
                        }
                    });

                    // Accordion
                    const accordion = collapseTrigger.closest(".accordion");
                    if (accordion) {
                        const accordionTriggers = accordion.querySelectorAll(selector);
                        accordionTriggers.forEach((accordionTrigger) => {
                            if (accordionTrigger !== collapseTrigger) {
                                accordionTrigger.classList.remove("active");
                            }
                        });

                        const accordions = accordion.querySelectorAll(".collapse");
                        accordions.forEach((accordion) => {
                            if (accordion.classList.contains("open")) {
                                close(accordion);
                            }
                        });
                    }
                };

                on("body", "click", selector, (event) => {
                    toggleCollapse(event);
                });
            };

            collapse();

            const darkMode = () => {
                const root = document.documentElement;

                const scheme = localStorage.getItem("scheme");

                const darkModeToggler = document.getElementById("darkModeToggler");

                scheme && root.classList.add(scheme);

                if (scheme === "dark") {
                    if (!darkModeToggler) return;
                    darkModeToggler.checked = "checked";
                }

                // Enable Dark Mode
                const enableDarkMode = () => {
                    root.classList.remove("light");
                    root.classList.add("dark");
                    localStorage.setItem("scheme", "dark");
                };

                // Disable Dark Mode
                const disableDarkMode = () => {
                    root.classList.remove("dark");
                    root.classList.add("light");
                    localStorage.removeItem("scheme");
                };

                // Check Dark Mode
                const checkDarkMode = (darkModeToggler) => {
                    if (root.classList.contains("dark") || !darkModeToggler.checked) {
                        return true;
                    } else {
                        return false;
                    }
                };

                // Toggler
                darkModeToggler.addEventListener("change", () => {
                    if (checkDarkMode(darkModeToggler)) {
                        disableDarkMode();
                    } else {
                        enableDarkMode();
                    }
                });
            };

            darkMode();

// Custom File Input
            const customFileInput = () => {
                on("body", "change", 'input[type="file"]', (event) => {
                    const filename = event.target.value.split("\\").pop();
                    event.target.parentNode.querySelector(".file-name").innerHTML = filename;
                });
            };

            customFileInput();

// Fullscreen
            const fullscreen = () => {
                const fullScreenToggler = document.getElementById("fullScreenToggler");

                if (!fullScreenToggler) return;

                const element = document.documentElement;

                // Open fullscreen
                const openFullscreen = () => {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.webkitRequestFullscreen) {
                        element.webkitRequestFullscreen();
                    } else if (element.msRequestFullscreen) {
                        element.msRequestFullscreen();
                    }
                };

                // Close fullscreen
                const closeFullscreen = () => {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.webkitExitFullscreen) {
                        document.webkitExitFullscreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                };

                // Check fullscreen
                const checkFullscreen = () => {
                    if (
                        document.fullscreenElement ||
                        document.webkitFullscreenElement ||
                        document.mozFullScreenElement ||
                        document.msFullscreenElement
                    ) {
                        return true;
                    }

                    return false;
                };

                // Toggle Button Icon
                const togglerBtnIcon = () => {
                    if (fullScreenToggler.classList.contains("la-expand-arrows-alt")) {
                        fullScreenToggler.classList.remove("la-expand-arrows-alt");
                        fullScreenToggler.classList.add("la-compress-arrows-alt");
                    } else {
                        fullScreenToggler.classList.remove("la-compress-arrows-alt");
                        fullScreenToggler.classList.add("la-expand-arrows-alt");
                    }
                };

                fullScreenToggler.addEventListener("click", () => {
                    if (checkFullscreen()) {
                        closeFullscreen();
                    } else {
                        openFullscreen();
                    }

                    togglerBtnIcon();
                });
            };

            fullscreen();

// Menu
            const menu = () => {
                const menu = document.querySelector(".menu-bar");

                if (!menu) return;

                // Hide Menu Detail
                const hideMenuDetail = () => {
                    menu.querySelectorAll(".menu-detail.open").forEach((menuDetail) => {
                        hideOverlay();
                        menuDetail.classList.remove("open");
                    });
                };

                // Hide Menu - When Clicked Elsewhere
                document.addEventListener("click", (event) => {
                    if (
                        !event.target.closest(".menu-items a") &&
                        !event.target.closest(".menu-detail")
                    ) {
                        hideMenuDetail();
                    }
                });

                // Menu Links
                on(".menu", "click", ".menu-items a", (event) => {
                    hideMenuDetail();

                    const menuName = event.target.closest(".menu-items a").dataset.target;
                    const selectedMenu = menu.querySelector(
                        '.menu-detail[data-menu="' + menuName + '"]'
                    );

                    if (selectedMenu) {
                        showOverlay(true);
                        selectedMenu.classList.add("open");
                    } else {
                        hideOverlay();
                    }
                });

                // Toggle Menu
                const toggleMenu = () => {
                    if (menu.classList.contains("hidden")) {
                        menu.classList.remove("hidden");
                        document.body.classList.remove("menu-hidden");
                        document.body.classList.add("menu-icon-only");
                        menu.classList.add("open", "icon-only");
                    } else if (menu.classList.contains("icon-only")) {
                        menu.classList.remove("icon-only");
                        document.body.classList.remove("menu-icon-only");
                    } else {
                        menu.classList.add("hidden");
                        document.body.classList.add("menu-hidden");
                        menu.classList.remove("open");
                    }
                };

                on(".top-bar", "click", "[data-toggle='menu']", (event) => {
                    toggleMenu(event);
                });
            };

            menu();

// Modal
            const modal = () => {
                // Show
                const showModal = (modal) => {
                    showOverlay();
                    modal.classList.add("active");
                    const animation = modal.dataset.animations.split(", ")[0];
                    const modalContent = modal.querySelector(".modal-content");
                    animateCSS(modalContent, animation);

                    modal.addEventListener("click", (event) => {
                        if (modal.dataset.staticBackdrop !== undefined) return;
                        if (modal !== event.target) return;
                        closeModal(modal);
                    });
                };

                on("body", "click", '[data-toggle="modal"]', (event) => {
                    const modal = document.querySelector(event.target.dataset.target);
                    showModal(modal);
                });

                // Close
                const closeModal = (modal) => {
                    hideOverlay();
                    const animation = modal.dataset.animations.split(", ")[1];
                    const modalContent = modal.querySelector(".modal-content");
                    animateCSS(modalContent, animation, () => {
                        modal.classList.remove("active");
                    });
                };

                on(".modal", "click", '[data-dismiss="modal"]', (event) => {
                    const modal = event.target.closest(".modal");
                    closeModal(modal);
                });
            };

            modal();

// Overlay
// Show
            const showOverlay = (workspace) => {
                if (document.querySelector(".overlay")) return;

                document.body.classList.add("overlay-show");

                const overlay = document.createElement("div");
                if (workspace) {
                    overlay.setAttribute("class", "overlay workspace");
                } else {
                    overlay.setAttribute("class", "overlay");
                }

                document.body.appendChild(overlay);
                overlay.classList.add("active");
            };

// Hide
            const hideOverlay = () => {
                overlayToRemove = document.querySelector(".overlay");

                if (!overlayToRemove) return;

                document.body.classList.remove("overlay-show");

                overlayToRemove.classList.remove("active");
                document.body.removeChild(overlayToRemove);
            };

// Rating Stars
            const ratingStars = () => {
                rateStars = (event) => {
                    const starsContainer = event.target.closest(".rating-stars");
                    const stars = Array.from(starsContainer.children);
                    const totalStars = stars.length;
                    const index = stars.indexOf(event.target);
                    let count = 0;
                    count = totalStars - index;
                    stars.forEach((star) => star.classList.remove("active"));

                    event.target.classList.add("active");

                    console.log("You have rated " + count + " stars.");
                };

                on("body", "click", ".rating-stars", (event) => {
                    rateStars(event);
                });
            };

            ratingStars();

// Show Password
            const showPassword = () => {
                // Toggle Show Password
                const toggleShowPassword = (showPasswordBtn) => {
                    const password = showPasswordBtn
                        .closest(".form-control-addon-within")
                        .querySelector("input");

                    if (password.type === "password") {
                        password.type = "text";
                        showPasswordBtn.classList.remove("text-gray-600", "dark:text-gray-600");
                        showPasswordBtn.classList.add("text-primary", "dark:text-primary");
                    } else {
                        password.type = "password";
                        showPasswordBtn.classList.remove("text-primary", "dark:text-primary");
                        showPasswordBtn.classList.add("text-gray-600", "dark:text-gray-600");
                    }
                };

                on("body", "click", '[data-toggle="password-visibility"]', (event) => {
                    const showPasswordBtn = event.target.closest(
                        '[data-toggle="password-visibility"]'
                    );
                    toggleShowPassword(showPasswordBtn);
                });
            };

            showPassword();

            const sidebar = () => {
                // Toggle Sidebar
                const toggleSidebar = () => {
                    const sidebar = document.querySelector(".sidebar");
                    if (sidebar.classList.contains("open")) {
                        sidebar.classList.remove("open");
                        hideOverlay();
                    } else {
                        sidebar.classList.add("open");
                        showOverlay(true);
                    }
                };

                on("body", "click", '[data-toggle="sidebar"]', () => {
                    toggleSidebar();
                });
            };

            sidebar();

            const tabs = () => {
                on("body", "click", '[data-toggle="tab"]', (event) => {
                    const trigger = event.target.closest('[data-toggle="tab"]');

                    const tabs = trigger.closest(".tabs");
                    const targetedTab = tabs.querySelector(trigger.dataset.target);
                    const activeTabTrigger = tabs.querySelector(".tab-nav .active");
                    const activeTab = tabs.querySelector(".collapse.open");

                    if (activeTabTrigger === trigger) return;

                    // Trigger
                    activeTabTrigger.classList.remove("active");
                    trigger.classList.add("active");

                    // Tab
                    // Close
                    activeTab.style.overflow = "hidden";
                    let tabHeight = activeTab.scrollHeight + "px";
                    activeTab.style.height = tabHeight;
                    setTimeout(() => {
                        activeTab.style.height = 0;
                        activeTab.style.opacity = 0;
                    }, 200);
                    animateNode(activeTab, () => {
                        activeTab.classList.remove("open");

                        // Open
                        tabHeight = targetedTab.scrollHeight + "px";
                        setTimeout(() => {
                            targetedTab.style.height = tabHeight;
                            targetedTab.style.opacity = 1;
                        }, 200);
                        animateNode(targetedTab, () => {
                            targetedTab.style.overflow = "visible";
                            targetedTab.style.height = null;
                            targetedTab.classList.add("open");
                        });
                    });
                });
            };

            tabs();

// Tippy
            const customTippy = () => {
                // Menu tooltip
                tippy.delegate("body", {
                    target: '.icon-only [data-toggle="tooltip-menu"]',
                    touch: ["hold", 500],
                    theme: "light-border tooltip",
                    offset: [0, 12],
                    interactive: true,
                    animation: "scale",
                    placement: "right",
                    appendTo: () => document.body,
                });

                // General tooltip
                tippy('[data-toggle="tooltip"]', {
                    theme: "light-border tooltip",
                    touch: ["hold", 500],
                    offset: [0, 12],
                    interactive: true,
                    animation: "scale",
                });

                // Popover
                tippy('[data-toggle="popover"]', {
                    theme: "light-border popover",
                    offset: [0, 12],
                    interactive: true,
                    allowHTML: true,
                    trigger: "click",
                    animation: "shift-toward-extreme",
                    content: (reference) => {
                        const title = reference.dataset.popoverTitle;
                        const content = reference.dataset.popoverContent;
                        const popover =
                            "<h5>" + title + "</h5>" + '<div class="mt-5">' + content + "</div>";
                        return popover;
                    },
                });

                // Dropdown
                tippy('[data-toggle="dropdown-menu"]', {
                    theme: "light-border",
                    zIndex: 45,
                    offset: [0, 8],
                    arrow: false,
                    placement: "bottom-start",
                    interactive: true,
                    allowHTML: true,
                    animation: "shift-toward-extreme",
                    content: (reference) => {
                        let dropdownMenu = reference
                            .closest(".dropdown")
                            .querySelector(".dropdown-menu");
                        dropdownMenu = dropdownMenu.outerHTML;
                        return dropdownMenu;
                    },
                });

                // Custom Dropdown
                tippy('[data-toggle="custom-dropdown-menu"]', {
                    theme: "light-border",
                    zIndex: 45,
                    offset: [0, 8],
                    arrow: false,
                    placement: "bottom-start",
                    interactive: true,
                    allowHTML: true,
                    animation: "shift-toward-extreme",
                    content: (reference) => {
                        let dropdownMenu = reference
                            .closest(".dropdown")
                            .querySelector(".custom-dropdown-menu");
                        dropdownMenu = dropdownMenu.outerHTML;
                        return dropdownMenu;
                    },
                });

                // Search & Select
                tippy('[data-toggle="search-select"]', {
                    theme: "light-border",
                    offset: [0, 8],
                    maxWidth: "none",
                    arrow: false,
                    placement: "bottom-start",
                    trigger: "click",
                    interactive: true,
                    allowHTML: true,
                    animation: "shift-toward-extreme",
                    content: (reference) => {
                        let dropdownMenu = reference
                            .closest(".search-select")
                            .querySelector(".search-select-menu");
                        dropdownMenu = dropdownMenu.outerHTML;
                        return dropdownMenu;
                    },
                    appendTo(reference) {
                        return reference.closest(".search-select");
                    },
                });
            };

            customTippy();

// Toasts

            const toasts = () => {
                const toastsContainer = document.getElementById("toasts-container");

                const toastCloseSelector = '[data-dismiss="toast"]';

                // Toast
                const createToast = (toast) => {
                    const title = toast.dataset.title;
                    const content = toast.dataset.content;
                    const time = toast.dataset.time;
                    let newToast =
                        '<div class="toast">' +
                        '<div class="toast-wrapper mb-5">' +
                        '<div class="toast-header">' +
                        "<h5>" +
                        title +
                        "</h5>" +
                        "<small>" +
                        time +
                        "</small>" +
                        '<button type="button" class="close" data-dismiss="toast">&times</button>' +
                        "</div>" +
                        '<div class="toast-body">' +
                        content +
                        "</div>" +
                        "</div>" +
                        "</div>";

                    newToast = new DOMParser().parseFromString(newToast, "text/html").body
                        .firstChild;

                    newToast.querySelector(toastCloseSelector).addEventListener("click", () => {
                        closeToast(newToast);
                    });

                    toastsContainer.appendChild(newToast);
                    animateCSS(newToast, "fadeInUp");
                };

                on("body", "click", '[data-toggle="toast"]', (event) => {
                    const toast = event.target;
                    createToast(toast);
                });

                // Close Toast
                const closeToast = (toast) => {
                    animateCSS(toast, "fadeOutUp", () => {
                        toast.style.opacity = 0;
                        toast.style.overflow = "hidden";
                        const toastHeight = toast.scrollHeight + "px";
                        toast.style.height = toastHeight;
                        setTimeout(() => {
                            toast.style.height = 0;
                        });
                        animateNode(toast, () => {
                            toast.parentNode.removeChild(toast);
                        });
                    });
                };

                on("body", "click", toastCloseSelector, (event) => {
                    toast = event.target.closest(".toast");
                    closeToast(toast);
                });
            };

            toasts();

        }


        this.$nextTick(menu());
    },
    methods:{
    },
    computed:{
        companyComputed(){
            return ":"+this.company1;
        },
        iscompany(){
            if (this.company1!=""){/*有公司名，怎么说明已登录*/
                return true
            }else {
                return false
            }
        }
    }

})


