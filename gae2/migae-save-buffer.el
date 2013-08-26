;; to install add this to your init file:
;; (add-hook 'clojure-mode-hook
;;              (lambda ()
;;                (define-key clojure-mode-map "\C-xC-s"
;;                            'migae-save-buffer)))

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
