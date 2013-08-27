;; in clojure mode, on save, copy from src dir to webapp classes dir
;; so changes will be reloaded under Google Appengine dev_appserver.

;; NB: designed to be used with .dir-locals.el in projet directory containing:
;; ((clojure-mode
;;   (warroot . "$HOME/migae-examples/gae2/war")
;;   (srcroot . "$HOME/migae-examples/gae2/src")))
;; WARNING!  Edit these paths so they are correct for your system!
;; See gae2 project for example.

;; to install, put this file in your load path, byte compile it,
;; and add this to your init file:
;; (load (expand-file-name "~/.emacs.d/elisp/migae-save-buffer"))
;; (add-hook 'clojure-mode-hook
;; 	  (lambda ()
;; 	    (message "clojure-mode-hook migae")
;; 	    (define-key clojure-mode-map (kbd "C-x C-s") 'migae-save-buffer)))

(defun migae-save-buffer (&optional args)
  (interactive "p")
  (save-buffer)
  (let* ((srcroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'srcroot file-local-variables-alist)))))
	 (warroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'warroot file-local-variables-alist)))))
	 (srcfile (buffer-file-name))
	 (relname (file-relative-name srcfile srcroot))
	 (tgtfile (concat warroot "WEB-INF/classes/" relname)))
    ;; (message "srcroot %s\nreltest %s" srcroot (file-relative-name srcfile srcroot))
    (message "copying %s to %s..." srcfile tgtfile)
    (make-directory (file-name-directory tgtfile) t)
    (copy-file srcfile tgtfile t)))

;; (and modp (memq args '(4 64)) (setq buffer-backed-up nil))))
(provide 'migae-save-buffer)
